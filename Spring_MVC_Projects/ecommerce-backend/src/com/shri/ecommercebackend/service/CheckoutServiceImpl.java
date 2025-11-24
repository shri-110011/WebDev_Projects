package com.shri.ecommercebackend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.InvalidItemReason;
import com.shri.ecommercebackend.dao.InventoryEventLogDAO;
import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dao.ReservationDAO;
import com.shri.ecommercebackend.dto.InvalidItemDTO;
import com.shri.ecommercebackend.dto.PriceMismatchItemDTO;
import com.shri.ecommercebackend.dto.ProductInventoryDTO;
import com.shri.ecommercebackend.dto.ReservedItemDTO;
import com.shri.ecommercebackend.dto.UnavailableItemDTO;
import com.shri.ecommercebackend.entity.InventoryEventLog;
import com.shri.ecommercebackend.entity.InventoryEventStatus;
import com.shri.ecommercebackend.entity.InventoryEventType;
import com.shri.ecommercebackend.response.ReservationStatus;
import com.shri.ecommercebackend.response.ReserveItemsResponse;
import com.shri.ecommercebackend.validation.CartItem;
import com.shri.ecommercebackend.validation.ReserveItemsRequest;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private InventoryEventLogDAO inventoryEventLogDAO;
	
	@Autowired
	private ReservationDAO reservationDAO;

	@Autowired
	private RedisService redisService;

	@Override
	@Transactional
	public ReserveItemsResponse reserveCartItems(ReserveItemsRequest reserveItemsRequest) {
		/*
		 * Todos:
		 * 
		 * Loop through each item in cart and get the productIds of all items that are
		 * not there in Redis.
		 * 
		 * If there are some items that are not there in Redis fetch them from the
		 * database.
		 * 
		 * Loop through the cart items: - If the item is still not there in Redis
		 * consider the item for invalidItems key in response.
		 * 
		 * - Check the availability for each item. - If the item is available consider
		 * the item for reservedItems key in response. - Else consider the item for
		 * unavailableItems key in response.
		 * 
		 * - Validate the price of each item by checking the price that user saw against
		 * the latest price. - If the price match then proceed with next step. - Else
		 * consider it for priceMismatchItems key in response.
		 * 
		 * - Update the availableQuantity for all available items in Redis by
		 * decrementing it.
		 * 
		 * - And save the reserved items in database.
		 * 
		 */
		
		List<CartItem> cartItems = reserveItemsRequest.getCartItems();
		
		Map<Integer, InvalidItemReason> invalidItemsMap = getInvalidItems(cartItems);
		
		List<String> luaScriptResults = redisService.executeLuaScriptToReserveItems(cartItems, invalidItemsMap);
		
		List<CartItem> validatedCartItems = getValidatedCartItems(luaScriptResults, cartItems, invalidItemsMap);
		
		List<InventoryEventLog> inventories = getInventoryEventLogs(validatedCartItems);
		
		inventoryEventLogDAO.insertItems(inventories);
		
		int reservationId =  reservationDAO.getReservationId(reserveItemsRequest.getUserId(), validatedCartItems, inventories);
		System.out.println("reservationId: " + reservationId);
		
		return prepareReserveItemsResponse(luaScriptResults, cartItems, 
				invalidItemsMap, reservationId);
	}
	
	private Map<Integer, InvalidItemReason> getInvalidItems(List<CartItem> cartItems) {
		List<Integer> productIds = cartItems.stream()
				.map(cartItem -> cartItem.getProductId())
				.collect(Collectors.toList());
		
		List<Integer> productIdsOfItemsNotPresentInCache = redisService
				.getProductIdsOfItemsNotPresentInCache(productIds);
		
		List<ProductInventoryDTO> productInventoryDTO = productDAO
				.getProductsInventoryInfo(productIdsOfItemsNotPresentInCache);
		
		redisService.loadProductsIntoCache(productInventoryDTO);
		
		List<Integer> productIdsNotFoundInDB = redisService.getProductIdsOfItemsNotPresentInCache(
				productIdsOfItemsNotPresentInCache);
		
		Map<Integer, InvalidItemReason> invalidItemsMap = new TreeMap<Integer, InvalidItemReason>();
		
		for(Integer productIdNotFoundInDB : productIdsNotFoundInDB) {
			invalidItemsMap.put(productIdNotFoundInDB, InvalidItemReason.Item_Not_Found_In_DB);
		}
		
		return invalidItemsMap;
	}
	
	private List<CartItem> getValidatedCartItems(List<String> luaScriptResults, List<CartItem> cartItems, 
			Map<Integer, InvalidItemReason> invalidItemsMap) {
		
		List<CartItem> validatedCartItems = new ArrayList<>();
		int i = 0;
		
		for(CartItem cartItem : cartItems) {
			if(!invalidItemsMap.containsKey(cartItem.getProductId())) {
				String statusCode = luaScriptResults.get(i);
				if(statusCode.equals("0")) i += 2;
				else if(statusCode.equals("1")) i+= 2;
				else {
					validatedCartItems.add(cartItem);
					i++;
				}
			}
		}
		
		return validatedCartItems;
	}
	
	private List<InventoryEventLog> getInventoryEventLogs(List<CartItem> validatedCartItems) {
		List<InventoryEventLog> inventoryEventLogs = new ArrayList<>();
		
		for(CartItem cartItem : validatedCartItems) {
			InventoryEventLog inventoryEventLog = new InventoryEventLog();
			inventoryEventLog.setProductId(cartItem.getProductId());
			inventoryEventLog.setQuantity(cartItem.getQuantity());
			inventoryEventLog.setChangeType(InventoryEventType.RESERVATION);
			inventoryEventLog.setStatus(InventoryEventStatus.ACTIVE);
			inventoryEventLogs.add(inventoryEventLog);
		}
		
		return inventoryEventLogs;
	}
		
	private ReserveItemsResponse prepareReserveItemsResponse(List<String> luaScriptResults, 
			List<CartItem> cartItems, 
			Map<Integer, InvalidItemReason> invalidItemsMap, int reservationId) {
		List<InvalidItemDTO> invalidItemsDTOs = new ArrayList<>();
		List<UnavailableItemDTO> unavailableItemsDTOs = new ArrayList<>();
		List<PriceMismatchItemDTO> priceMismatchItemsDTOs = new ArrayList<>();
		List<ReservedItemDTO> reservedItemsDTOs = new ArrayList<>();
				
		int i = 0, productId;
	    for(CartItem cartItem : cartItems) {
	    	productId = cartItem.getProductId();
	    	if(invalidItemsMap.containsKey(productId)) {
	    		if(invalidItemsMap.get(productId) == InvalidItemReason.Item_Not_Found_In_DB) {
	    			invalidItemsDTOs.add(new InvalidItemDTO(productId, "Not found."));
	    		}
	    	}
	    	else {
				String statusCode = luaScriptResults.get(i);
				if(statusCode.equals("0")) {
					String message = luaScriptResults.get(i+1);
					unavailableItemsDTOs.add(new UnavailableItemDTO(productId, 
							cartItem.getQuantity(), message));
					i += 2;
				}
				else if(statusCode.equals("1")) {
					String currentPrice = luaScriptResults.get(i+1);
					priceMismatchItemsDTOs.add(new PriceMismatchItemDTO(productId, 
							cartItem.getPricePerUnit(), new BigDecimal(currentPrice)));
					i += 2;
				}
				else {
					reservedItemsDTOs.add(new ReservedItemDTO(productId, cartItem.getQuantity()));
					i += 1;
				}
	    	}
		}
		
	    ReservationStatus reservationStatus = ReservationStatus.Failed;
		
		if(unavailableItemsDTOs.size() == 0 && reservedItemsDTOs.size() > 0) reservationStatus = ReservationStatus.Complete;
		else if(unavailableItemsDTOs.size() > 0 && reservedItemsDTOs.size() > 0) reservationStatus = ReservationStatus.Partial;
		
		return new ReserveItemsResponse(reservationId, reservationStatus, reservedItemsDTOs, unavailableItemsDTOs,
				invalidItemsDTOs, priceMismatchItemsDTOs);
	}
}

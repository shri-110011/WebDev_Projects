package com.shri.ecommercebackend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.CheckoutDAO;
import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dto.ProductInventoryDTO;
import com.shri.ecommercebackend.validation.CartItem;
import com.shri.ecommercebackend.validation.ReserveItemsRequest;

import redis.clients.jedis.Jedis;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	private CheckoutDAO checkoutDAO;

	@Autowired
	private Jedis jedis;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductLoadService productLoadService;

	@Override
	@Transactional
	public int reserveCartItems(ReserveItemsRequest reserveItemsRequest) {
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
		Map<String, List<Integer>> map = fetchItemsNotPresentInCacheFromDB(cartItems);

		List<Integer> invalidItemsProductIds = getInvalidItemsProductIds(map.get("itemsNotPresentInCache"));
		List<Integer> productIdsOfItemsPresentInCache = map.get("itemsPresentInCache");
		
		int[] validationResults = validateStockAvailabilityAndPrice(cartItems, invalidItemsProductIds);

		return checkoutDAO.reserveCartItems(reserveItemsRequest);
	}

	private Map<String, List<Integer>> checkForCartItemsInCache(List<CartItem> cartItems) {
		Map<String, List<Integer>> map = new HashMap<>();
		List<Integer> productIdsOfItemsPresentInCache = new ArrayList<>();
		List<Integer> productIdsOfItemsNotPresentInCache = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			Map<String, String> productInventoryInfo = jedis.hgetAll("productsInventory:" + cartItem.getProductId());
			System.out.println("productInventoryInfo: " + productInventoryInfo);

			if (productInventoryInfo.size() == 0) {
				productIdsOfItemsNotPresentInCache.add(cartItem.getProductId());
			}
			else {
				productIdsOfItemsPresentInCache.add(cartItem.getProductId());
			}
		}
		
		map.put("itemsPresentInCache", productIdsOfItemsPresentInCache);
		map.put("itemsNotPresentInCache", productIdsOfItemsNotPresentInCache);

		System.out.println("Products not present in Redis: " + productIdsOfItemsNotPresentInCache);

		return map;
	}

	private Map<String, List<Integer>> fetchItemsNotPresentInCacheFromDB(List<CartItem> cartItems) {
		Map<String, List<Integer>> map = checkForCartItemsInCache(cartItems);
		List<ProductInventoryDTO> productInventoryDTO = productDAO
				.getProductsInventoryInfo(map.get("itemsNotPresentInCache"));
		productLoadService.loadProductsIntoCache(productInventoryDTO);

		return map;
	}

	private List<Integer> getInvalidItemsProductIds(List<Integer> productIdsOfItemsNotPresentInCache) {
		List<Integer> invalidItemsProductIds = new ArrayList<>();
		for (Integer productIdOfItemNotPresentInCache : productIdsOfItemsNotPresentInCache) {
			Map<String, String> productInventoryInfo = jedis
					.hgetAll("productsInventory:" + productIdOfItemNotPresentInCache);

			if (productInventoryInfo.size() == 0) {
				invalidItemsProductIds.add(productIdOfItemNotPresentInCache);
			}
		}
		return invalidItemsProductIds;
	}

	private int[] validateStockAvailabilityAndPrice(List<CartItem> cartItems, 
			List<Integer> invalidItemsProductIds) {
		String luaScript;
		try {
			luaScript = new String(Files.readAllBytes(Paths.get("D:\\WebDev_Projects\\Spring_MVC_Projects\\ecommerce-backend\\src\\resources\\decrement_stock.lua")));
			
			Set<Integer> inValidProductIdsSet = new HashSet<>(invalidItemsProductIds);

			// Keys and Arguments setup
		    List<String> keys = new ArrayList<>();
		    List<String> args = new ArrayList<>(); // qty and price pairs for each product

		    for(CartItem cartItem : cartItems) {
		    	if(!inValidProductIdsSet.contains(cartItem.getProductId())) {
		    		keys.add("productsInventory:" + cartItem.getProductId());
		    		args.add(Integer.toString(cartItem.getQuantity()));
		    		args.add(cartItem.getPricePerUnit().toString());
		    	}
		    }
		    
		    System.out.println("keys: " + keys);
		    System.out.println("args: " + args);
		    
		    // Execute Lua script with all keys and arguments in one call
		    Object result = jedis.eval(luaScript, keys, args);
		    System.out.println("Script result: " + result);
		    
		    return (int[])result;
		} catch (IOException e) {
			e.printStackTrace();
			return new int[0];
		}
	}
	
	private ReserveItemsResponse prepareReserveItemsResponse(int[] validationResults, 
			List<CartItem> cartItems, List<Integer> invalidItems) {
		return null;
	}
}

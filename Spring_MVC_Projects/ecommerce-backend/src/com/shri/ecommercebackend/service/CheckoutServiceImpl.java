package com.shri.ecommercebackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		/* Todos:
		 * 
		 * Loop through each item in cart and get the productIds of all items that are not there in Redis.
		 * 
		 * If there are some items that are not there in Redis fetch them from the database.
		 * 
		 * Loop through the cart items:
		 * - If the item is still not there in Redis consider the item for invalidItems key in response.
		 * 
		 * - Validate the price of each item by checking the price that user saw against the latest price.
		 * 	- If the price match then proceed with next step.
		 * 	- Else consider it for priceMismatchItems key in response.
		 * 
		 * - Check the availability for each item.
		 * 	- If the item is available consider the item for reservedItems key in response.
		 * 	- Else consider the item for unavailableItems key in response.
		 * 
		 * - Update the availableQuantity for all available items in Redis by decrementing it.
		 * - And save the reserved items in database.
		 * 
		 * */
		
		List<CartItem> cartItems = reserveItemsRequest.getCartItems();
		List<Integer> productIds = new ArrayList<>();
		
		for(CartItem cartItem : cartItems) {
			Map<String, String> productInventoryInfo = jedis.hgetAll("productsInventory:" + cartItem.getProductId());
			System.out.println("productInventoryInfo: " + productInventoryInfo);
			
			if(productInventoryInfo.size() == 0) {
				productIds.add(cartItem.getProductId());
			}
		}
		
		System.out.println("Products not present in Redis: " + productIds);
		
		List<ProductInventoryDTO> productInventoryDTO = productDAO.getProductsInventoryInfo(productIds);
		productLoadService.loadProductsIntoCache(productInventoryDTO);
		
		return checkoutDAO.reserveCartItems(reserveItemsRequest);
	}

}

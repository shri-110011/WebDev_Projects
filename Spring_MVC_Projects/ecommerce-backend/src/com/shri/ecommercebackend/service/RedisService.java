package com.shri.ecommercebackend.service;

import java.util.List;
import java.util.Map;

import com.shri.ecommercebackend.dao.InvalidItemReason;
import com.shri.ecommercebackend.dto.ProductInventoryDTO;
import com.shri.ecommercebackend.validation.CartItem;

public interface RedisService {
	
	public void initializeCacheOnStartup();
	
	public void loadProductsIntoCache(List<ProductInventoryDTO> productIdQuantityDTOs);
	
	public List<Integer> getProductIdsOfItemsNotPresentInCache(List<Integer> productIds);
	
	public List<String> executeLuaScriptToReserveItems(List<CartItem> cartItems, 
			Map<Integer, InvalidItemReason> invalidItemsMap);
	
	public void executeLuaScriptToIncreaseAvailableQuantity(Map<Integer, Integer> productIdQuantityMap);

}

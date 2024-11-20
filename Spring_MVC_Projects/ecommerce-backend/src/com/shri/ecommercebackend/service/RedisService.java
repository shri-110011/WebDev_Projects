package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.dto.ProductInventoryDTO;

public interface RedisService {
	
	public void initializeCacheOnStartup();
	
	public void loadProductsIntoCache(List<ProductInventoryDTO> productIdQuantityDTOs);
	
	public List<Integer> getProductIdsOfItemsNotPresentInCache(List<Integer> productIds);

}

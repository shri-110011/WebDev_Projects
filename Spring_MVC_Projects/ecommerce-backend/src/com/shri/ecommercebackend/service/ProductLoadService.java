package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.dto.ProductInventoryDTO;

public interface ProductLoadService {
	
	public void initializeCacheOnStartup();
	
	public void loadProductsIntoCache(List<ProductInventoryDTO> productInventoryDTO);

}

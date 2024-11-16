package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.dto.ProductInventoryDTO;

public interface RedisService {
	
	public void loadProductsIdAndQuantityIntoRedis(List<ProductInventoryDTO> productIdQuantityDTOs);

}

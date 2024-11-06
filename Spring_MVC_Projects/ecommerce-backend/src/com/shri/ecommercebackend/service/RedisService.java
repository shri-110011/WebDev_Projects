package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.dto.ProductIdQuantityDTO;

public interface RedisService {
	
	public void loadProductsIdAndQuantityIntoRedis(List<ProductIdQuantityDTO> productIdQuantityDTOs);

}

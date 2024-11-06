package com.shri.ecommercebackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.ecommercebackend.dto.ProductIdQuantityDTO;

import redis.clients.jedis.Jedis;

@Service
public class RedisServiceImpl implements RedisService {
	
	private final Jedis jedis;
	
	@Autowired
	public RedisServiceImpl(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void loadProductsIdAndQuantityIntoRedis(List<ProductIdQuantityDTO> productIdQuantityDTOs) {
		
		Map<String, String> productData = new HashMap<>();
		for(ProductIdQuantityDTO productIdQuantityDTO : productIdQuantityDTOs) {
		    productData.put(Integer.toString(productIdQuantityDTO.getProductId()), 
		                    Integer.toString(productIdQuantityDTO.getAvailableQuantity()));
		}
		
		jedis.hmset("productsIdAndQuantity", productData);
		
		
	}

}
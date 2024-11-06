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
		
//		String productDataArray[] = productData.entrySet().stream()
//		         .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
//		         .toArray(String[]::new);
//		
//		System.out.println(Arrays.toString(productDataArray));
		
		jedis.hmset("productsIdAndQuantity", productData);
		
//		for(ProductIdQuantityDTO productIdQuantityDTO : productIdQuantityDTOs) {
//			jedis.set(Integer.toString(productIdQuantityDTO.getProductId()), 
//					Integer.toString(productIdQuantityDTO.getAvailableQuantity())
//					);
//		}
		
	}

}

package com.shri.ecommercebackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.ecommercebackend.dto.ProductInventoryDTO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

@Service
public class RedisServiceImpl implements RedisService {
	
	private final Jedis jedis;
	
	@Autowired
	public RedisServiceImpl(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void loadProductsIdAndQuantityIntoRedis(List<ProductInventoryDTO> productInventoryDTOs) {
		
		Map<String, String> productData = new HashMap<>();
		Pipeline pipeline = jedis.pipelined();
		
		for(ProductInventoryDTO productInventoryDTO : productInventoryDTOs) {
			productData.put("availableQuantity", Integer.toString(productInventoryDTO.getAvailableQuantity()));
			productData.put("price", productInventoryDTO.getPrice().toString());
			
			pipeline.hmset("productsInventory:" + Integer.toString(productInventoryDTO.getProductId()), productData);
		}
		
		pipeline.sync();		
	}

}

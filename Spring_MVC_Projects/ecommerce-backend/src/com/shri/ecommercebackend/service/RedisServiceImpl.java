package com.shri.ecommercebackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dto.ProductInventoryDTO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

@Service
public class RedisServiceImpl implements RedisService {
	
	private final Jedis jedis;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	public RedisServiceImpl(Jedis jedis) {
		this.jedis = jedis;
	}
	
	@Override
	@EventListener(ContextRefreshedEvent.class)
	@Transactional(readOnly = true)
	public void initializeCacheOnStartup() {
		List<Integer> productIds = getProductIdsOfItemsNotPresentInCache(List.of(1000003, 1000008));
		List<ProductInventoryDTO> productInventoryDTO = productDAO.getProductsInventoryInfo(productIds);
		loadProductsIntoCache(productInventoryDTO);
	}


	@Override
	public void loadProductsIntoCache(List<ProductInventoryDTO> productInventoryDTOs) {
		
		if(productInventoryDTOs.size() == 0) return;
		
		System.out.println("Products to be loaded into Redis...");
		System.out.println(productInventoryDTOs);
		
		Map<String, String> productData = new HashMap<>();
		Pipeline pipeline = jedis.pipelined();
		
		for(ProductInventoryDTO productInventoryDTO : productInventoryDTOs) {
			productData.put("availableQuantity", Integer.toString(productInventoryDTO.getAvailableQuantity()));
			productData.put("price", productInventoryDTO.getPrice().toString());
			
			pipeline.hmset("productsInventory:" + Integer.toString(productInventoryDTO.getProductId()), productData);
		}
		
		pipeline.sync();
		System.out.println("Products loaded into Redis...");
	}

	@Override
	public List<Integer> getProductIdsOfItemsNotPresentInCache(List<Integer> productIds) {
		if(productIds.size() == 0) return List.of();
		
		List<Integer> productIdsOfItemsNotPresentInCache = new ArrayList<>();
		for (Integer productId : productIds) {
			if (!jedis.hexists("productsInventory:" + productId, "price")) {
				productIdsOfItemsNotPresentInCache.add(productId);
			}
		}
		System.out.println("Products not present in cache: " + productIdsOfItemsNotPresentInCache);
		return productIdsOfItemsNotPresentInCache;
	}

}

package com.shri.ecommercebackend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.InvalidItemReason;
import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dto.ProductInventoryDTO;
import com.shri.ecommercebackend.validation.CartItem;

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
	
	@Override
	public List<String> executeLuaScriptToReserveItems(List<CartItem> cartItems, 
			Map<Integer, InvalidItemReason> invalidItemsMap) {
		System.out.println("Executing Lua script to reserve items...");
		String luaScript;
		try {
			luaScript = new String(Files.readAllBytes(Paths.get("D:\\WebDev_Projects\\Spring_MVC_Projects\\ecommerce-backend\\src\\resources\\decrement_stock.lua")));

			// Keys and Arguments setup
		    List<String> keys = new ArrayList<>();
		    List<String> args = new ArrayList<>(); // qty and price pairs for each product
		    
		    int productId;
		    for(CartItem cartItem : cartItems) {
		    	productId = cartItem.getProductId();
		    	if(!invalidItemsMap.containsKey(productId)) {
		    		keys.add("productsInventory:" + cartItem.getProductId());
		    		args.add(Integer.toString(cartItem.getQuantity()));
		    		args.add(cartItem.getPricePerUnit().toString());
		    	}
		    }
		    
		    System.out.println("keys: " + keys);
		    System.out.println("args: " + args);
		    
		    // Execute Lua script with all keys and arguments in one call
		    Object result = jedis.eval(luaScript, keys, args);
		    System.out.println("Script result: " + result);
		    
		    return (List<String>)result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void executeLuaScriptToIncreaseAvailableQuantity(Map<Integer, Integer> productIdQuantityMap) {
		List<Integer> productIds = new ArrayList<>(productIdQuantityMap.keySet());
		
		List<Integer> productIdsOfItemsNotPresentInCache = getProductIdsOfItemsNotPresentInCache(productIds);
		
		List<ProductInventoryDTO> productInventoryDTO = productDAO
				.getProductsInventoryInfo(productIdsOfItemsNotPresentInCache);
		
		loadProductsIntoCache(productInventoryDTO);
		
		System.out.println("Executing Lua script to increase available items quantity...");
		String luaScript;
		try {
			luaScript = new String(Files.readAllBytes(Paths.get("D:\\WebDev_Projects\\Spring_MVC_Projects\\ecommerce-backend\\src\\resources\\increment_stock.lua")));

			// Keys and Arguments setup
		    List<String> keys = new ArrayList<>();
		    List<String> args = new ArrayList<>(); // qty and price pairs for each product
		    
		    Set<Map.Entry<Integer, Integer>> entries = productIdQuantityMap.entrySet();
		    for(Map.Entry<Integer, Integer> entry : entries) {
	    		keys.add("productsInventory:" + entry.getKey());
	    		args.add(Integer.toString(entry.getValue()));
		    }
		    
		    System.out.println("keys: " + keys);
		    System.out.println("args: " + args);
		    
		    // Execute Lua script with all keys and arguments in one call
		    Object result = jedis.eval(luaScript, keys, args);
		    System.out.println("Script result: " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.shri.ecommercebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dto.ProductIdQuantityDTO;

@Service
public class ProductLoadServiceImpl implements ProductLoadService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private RedisService redisService;
	
	
	@Override
	@EventListener(ContextRefreshedEvent.class)
	@Transactional(readOnly = true)
	public void loadProductsIntoCache() {
		List<ProductIdQuantityDTO> productIdQuantityDTO = productDAO.getProductsIdAndQuantity();
		
		System.out.println(productIdQuantityDTO);
		
		redisService.loadProductsIdAndQuantityIntoRedis(productIdQuantityDTO);
		
		System.out.println("Products loaded into Redis...");
		
	}
	
}

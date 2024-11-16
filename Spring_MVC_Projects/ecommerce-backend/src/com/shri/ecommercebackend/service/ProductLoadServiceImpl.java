package com.shri.ecommercebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dto.ProductInventoryDTO;

@Service
public class ProductLoadServiceImpl implements ProductLoadService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private RedisService redisService;

	@Override
	@EventListener(ContextRefreshedEvent.class)
	@Transactional(readOnly = true)
	public void initializeCacheOnStartup() {
		List<ProductInventoryDTO> productInventoryDTO = productDAO.getProductsInventoryInfo(
					List.of(1000003, 1000008)
				);
		loadProductsIntoCache(productInventoryDTO);
	}
	
	@Override
	public void loadProductsIntoCache(List<ProductInventoryDTO> productInventoryDTO) {
		System.out.println(productInventoryDTO);

		redisService.loadProductsIdAndQuantityIntoRedis(productInventoryDTO);

		System.out.println("Products loaded into Redis...");
	}

}

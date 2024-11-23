package com.shri.ecommercebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.InventoryDAO;
import com.shri.ecommercebackend.entity.Inventory;

public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryDAO inventoryDAO;

	@Override
	@Transactional
	public List<Integer> insertItems(List<Inventory> inventories) {
		return inventoryDAO.insertItems(inventories);
	}

}

package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.entity.Inventory;

public interface InventoryService {
	
	public List<Integer> insertItems(List<Inventory> inventories);

}

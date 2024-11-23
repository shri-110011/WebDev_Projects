package com.shri.ecommercebackend.dao;

import java.util.List;

import com.shri.ecommercebackend.entity.Inventory;

public interface InventoryDAO {
	
	public List<Integer> insertItems(List<Inventory> inventories);
	
}

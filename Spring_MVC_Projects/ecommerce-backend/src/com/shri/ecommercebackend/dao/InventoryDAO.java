package com.shri.ecommercebackend.dao;

import java.util.List;

import com.shri.ecommercebackend.entity.Inventory;

public interface InventoryDAO {
	
	public void insertItems(List<Inventory> inventories);
	
}

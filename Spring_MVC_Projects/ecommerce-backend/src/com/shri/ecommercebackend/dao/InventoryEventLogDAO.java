package com.shri.ecommercebackend.dao;

import java.util.List;

import com.shri.ecommercebackend.entity.InventoryEventLog;

public interface InventoryEventLogDAO {
	
	public void insertItems(List<InventoryEventLog> inventoryEventLogs);
	
}

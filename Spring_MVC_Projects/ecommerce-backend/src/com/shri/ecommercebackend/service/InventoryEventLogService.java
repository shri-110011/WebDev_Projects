package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.entity.InventoryEventLog;

public interface InventoryEventLogService {
	
	public void insertItems(List<InventoryEventLog> inventoryEventLogs);

}

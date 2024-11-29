package com.shri.ecommercebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.InventoryEventLogDAO;
import com.shri.ecommercebackend.entity.InventoryEventLog;

public class InventoryServiceImpl implements InventoryEventLogService {

	@Autowired
	private InventoryEventLogDAO inventoryEventLogDAO;

	@Override
	@Transactional
	public void insertItems(List<InventoryEventLog> inventoryEventLogs) {
		inventoryEventLogDAO.insertItems(inventoryEventLogs);
	}

}

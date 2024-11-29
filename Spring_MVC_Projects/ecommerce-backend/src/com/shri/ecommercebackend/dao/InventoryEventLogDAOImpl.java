package com.shri.ecommercebackend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.entity.InventoryEventLog;

@Repository
public class InventoryEventLogDAOImpl implements InventoryEventLogDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertItems(List<InventoryEventLog> inventoryEventLogs) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		int inventoryEvenLogsSize = inventoryEventLogs.size();
		for(int i = 0; i < inventoryEvenLogsSize; i++) {
			InventoryEventLog inventory = inventoryEventLogs.get(i);
			currentSession.save(inventory);
			System.out.println(inventory);
			
			if (i % 20 == 0) {
				currentSession.flush(); // Flush every 20 items to execute the batch
				currentSession.clear(); // Clear the session to avoid memory issues
		    }
		}
	}

}

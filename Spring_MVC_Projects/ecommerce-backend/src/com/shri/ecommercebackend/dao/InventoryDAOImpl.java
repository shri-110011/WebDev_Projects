package com.shri.ecommercebackend.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.entity.Inventory;

@Repository
public class InventoryDAOImpl implements InventoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Integer> insertItems(List<Inventory> inventories) {
		Session currentSession = sessionFactory.getCurrentSession();
		List<Integer> inventoryIds = new ArrayList<>();
		
		int inventorySize = inventories.size();
		for(int i = 0; i < inventorySize; i++) {
			Inventory inventory = inventories.get(i);
			currentSession.save(inventory);
			inventoryIds.add(inventory.getInverntoryId());
			System.out.println(inventory);
			
			if (i % 20 == 0) {
				currentSession.flush(); // Flush every 20 items to execute the batch
				currentSession.clear(); // Clear the session to avoid memory issues
		    }
		}
				
		return inventoryIds;
	}

}

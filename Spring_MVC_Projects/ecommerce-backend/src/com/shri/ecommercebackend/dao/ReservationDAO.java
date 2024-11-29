package com.shri.ecommercebackend.dao;

import java.util.List;

import com.shri.ecommercebackend.entity.InventoryEventLog;
import com.shri.ecommercebackend.validation.CartItem;

public interface ReservationDAO {

	public int getReservationId(int userId, List<CartItem> validatedCartItems, 
			List<InventoryEventLog> inventoryEventLogs);
	
}

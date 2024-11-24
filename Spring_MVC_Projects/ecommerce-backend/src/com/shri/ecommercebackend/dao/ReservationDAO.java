package com.shri.ecommercebackend.dao;

import java.util.List;

import com.shri.ecommercebackend.entity.Inventory;

public interface ReservationDAO {

	public int getReservationId(int userId, List<Inventory> inventories);
	
}

package com.shri.ecommercebackend.dao;

import java.util.List;

public interface ReservationDAO {

	public int getReservationId(int userId, List<Integer> inventoryIds);
	
}

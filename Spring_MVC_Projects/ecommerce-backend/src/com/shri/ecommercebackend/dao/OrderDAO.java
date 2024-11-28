package com.shri.ecommercebackend.dao;

import com.shri.ecommercebackend.entity.Order;

public interface OrderDAO {

	public Order placeOrder(int reservationId);
	
}

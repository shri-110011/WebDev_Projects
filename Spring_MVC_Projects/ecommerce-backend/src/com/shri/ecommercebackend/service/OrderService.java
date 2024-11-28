package com.shri.ecommercebackend.service;

import com.shri.ecommercebackend.response.PlaceOrderResponse;

public interface OrderService {
	
	public PlaceOrderResponse placeOrder(int reservationId);

}

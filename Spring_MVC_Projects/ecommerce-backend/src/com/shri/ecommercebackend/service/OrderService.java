package com.shri.ecommercebackend.service;

import com.shri.ecommercebackend.response.CancelOrderResponse;
import com.shri.ecommercebackend.response.OrderStatusResponse;
import com.shri.ecommercebackend.response.PlaceOrderResponse;

public interface OrderService {
	
	public PlaceOrderResponse placeOrder(int reservationId);
	
	public CancelOrderResponse cancelOrder(int orderId);
	
	public OrderStatusResponse getOrderStatus(int orderId);

}

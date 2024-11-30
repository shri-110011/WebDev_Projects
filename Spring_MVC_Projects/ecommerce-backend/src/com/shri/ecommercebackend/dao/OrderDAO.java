package com.shri.ecommercebackend.dao;

import java.math.BigDecimal;
import java.util.List;

import com.shri.ecommercebackend.entity.Order;
import com.shri.ecommercebackend.entity.OrderItem;
import com.shri.ecommercebackend.response.OrderStatusResponse;

public interface OrderDAO {

	public Order placeOrder(int reservationId);
	
	public BigDecimal cancelOrder(int orderId);
	
	public List<OrderItem> getOrderItems(int orderId);
	
	public OrderStatusResponse getOrderStatus(int orderId);
	
}

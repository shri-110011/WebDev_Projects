package com.shri.ecommercebackend.response;

import java.math.BigDecimal;

import com.shri.ecommercebackend.entity.OrderStatus;

public class PlaceOrderResponse {
	private int orderId;
	private OrderStatus orderStatus;
	private BigDecimal totalAmount;
	private String message;
	
	public PlaceOrderResponse(int orderId, OrderStatus orderStatus, BigDecimal totalAmount, String message) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.message = message;
	}

	public int getOrderId() {
		return orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public String getMessage() {
		return message;
	}
}

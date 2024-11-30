package com.shri.ecommercebackend.response;

import java.math.BigDecimal;

import com.shri.ecommercebackend.entity.OrderStatus;

public class CancelOrderResponse {

	private int orderId;
	private OrderStatus orderStatus;
	private BigDecimal refundAmount;
	private String message;

	public CancelOrderResponse(int orderId, OrderStatus orderStatus, BigDecimal refundAmount, String message) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.refundAmount = refundAmount;
		this.message = message;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CancelOrderResponse [orderId=" + orderId + ", orderStatus=" + orderStatus + ", refundAmount="
				+ refundAmount + ", message=" + message + "]";
	}

}

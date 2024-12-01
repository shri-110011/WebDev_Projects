package com.shri.ecommercebackend.response;

import com.shri.ecommercebackend.dto.CancellationDetailsDTO;
import com.shri.ecommercebackend.dto.OrderDetailsDTO;
import com.shri.ecommercebackend.entity.OrderStatus;

public class OrderStatusResponse {

	private int orderId;
	private OrderStatus orderStatus;
	private CancellationDetailsDTO cancellationDetails;
	private OrderDetailsDTO orderDetails;
	private long timestamp;
	
	public OrderStatusResponse(int orderId, OrderStatus orderStatus, CancellationDetailsDTO cancellationDetails,
			OrderDetailsDTO orderDetailsDTO, long timestamp) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.cancellationDetails = cancellationDetails;
		this.orderDetails = orderDetailsDTO;
		this.timestamp = timestamp;
	}

	public int getOrderId() {
		return orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public CancellationDetailsDTO getCancellationDetailsDTO() {
		return cancellationDetails;
	}

	public OrderDetailsDTO getOrderDetailsDTO() {
		return orderDetails;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "OrderStatusResponse [orderId=" + orderId + ", orderStatus=" + orderStatus + ", cancellationDetails="
				+ cancellationDetails + ", orderDetails=" + orderDetails + ", timestamp=" + timestamp + "]";
	}
		
}

package com.shri.ecommercebackend.response;

import com.shri.ecommercebackend.dto.CancellationDetailsDTO;
import com.shri.ecommercebackend.dto.OrderDetailsDTO;
import com.shri.ecommercebackend.entity.OrderStatus;

public class OrderStatusResponse {

	private int orderId;
	private OrderStatus orderStatus;
	private CancellationDetailsDTO cancellationDetailsDTO;
	private OrderDetailsDTO orderDetailsDTO;
	private long timestamp;
	
	public OrderStatusResponse(int orderId, OrderStatus orderStatus, CancellationDetailsDTO cancellationDetailsDTO,
			OrderDetailsDTO orderDetailsDTO, long timestamp) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.cancellationDetailsDTO = cancellationDetailsDTO;
		this.orderDetailsDTO = orderDetailsDTO;
		this.timestamp = timestamp;
	}

	public int getOrderId() {
		return orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public CancellationDetailsDTO getCancellationDetailsDTO() {
		return cancellationDetailsDTO;
	}

	public OrderDetailsDTO getOrderDetailsDTO() {
		return orderDetailsDTO;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "OrderStatusResponse [orderId=" + orderId + ", orderStatus=" + orderStatus + ", cancellationDetailsDTO="
				+ cancellationDetailsDTO + ", orderDetailsDTO=" + orderDetailsDTO + ", timestamp=" + timestamp + "]";
	}
		
}

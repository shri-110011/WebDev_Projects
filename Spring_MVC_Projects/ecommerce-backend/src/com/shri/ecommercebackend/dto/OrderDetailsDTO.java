package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailsDTO {
	
	private List<OrderItemDTO> items;
	private LocalDateTime placedAt;
	private BigDecimal totalAmount;
	
	public OrderDetailsDTO(List<OrderItemDTO> items, LocalDateTime placedAt, BigDecimal totalAmount) {
		this.items = items;
		this.placedAt = placedAt;
		this.totalAmount = totalAmount;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public LocalDateTime getPlacedAt() {
		return placedAt;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	@Override
	public String toString() {
		return "OrderDetailsDTO [items=" + items + ", placedAt=" + placedAt + ", totalAmount=" + totalAmount + "]";
	}

}

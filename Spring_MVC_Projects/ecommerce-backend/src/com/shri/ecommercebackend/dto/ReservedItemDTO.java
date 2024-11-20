package com.shri.ecommercebackend.dto;

public class ReservedItemDTO {
	private int productId;
	private int quantity;
	
	public ReservedItemDTO(int productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}

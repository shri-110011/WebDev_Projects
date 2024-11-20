package com.shri.ecommercebackend.dto;

public class UnavailableItemDTO {
	private int productId;
	private int requestedQuantity;
	private String message;
	
	public UnavailableItemDTO(int productId, int requestedQuantity, String message) {
		this.productId = productId;
		this.requestedQuantity = requestedQuantity;
		this.message = message;
	}

	public int getProductId() {
		return productId;
	}

	public int getRequestedQuantity() {
		return requestedQuantity;
	}

	public String getMessage() {
		return message;
	}
}

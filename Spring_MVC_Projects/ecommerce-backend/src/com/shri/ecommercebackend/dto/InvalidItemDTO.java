package com.shri.ecommercebackend.dto;

public class InvalidItemDTO {
	private int productId;
	private String message;
	
	public InvalidItemDTO(int productId, String message) {
		this.productId = productId;
		this.message = message;
	}

	public int getProductId() {
		return productId;
	}

	public String getMessage() {
		return message;
	}
	
}

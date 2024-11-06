package com.shri.ecommercebackend.dto;

public class ProductIdQuantityDTO {
	
	private int productId;
	private int availableQuantity;
	
	public ProductIdQuantityDTO(int productId, int availableQuantity) {
		this.productId = productId;
		this.availableQuantity = availableQuantity;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	@Override
	public String toString() {
		return "ProductIdQuantityDTO [productId=" + productId + ", availableQuantity=" + availableQuantity + "]";
	}

}

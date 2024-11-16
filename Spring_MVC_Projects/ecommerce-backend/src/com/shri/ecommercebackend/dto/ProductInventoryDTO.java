package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;

public class ProductInventoryDTO {
	
	private int productId;
	private int availableQuantity;
	private BigDecimal price;
	
	public ProductInventoryDTO(int productId, int availableQuantity, BigDecimal price) {
		this.productId = productId;
		this.availableQuantity = availableQuantity;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "ProductInventoryDTO [productId=" + productId + ", availableQuantity=" + availableQuantity
				+ ", price=" + price + "]";
	}

}

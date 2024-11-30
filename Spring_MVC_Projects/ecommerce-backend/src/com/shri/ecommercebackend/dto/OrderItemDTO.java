package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
	
	private int productId;
	private String productName;
	private int quantity;
	private BigDecimal price;
	
	public OrderItemDTO(int productId, String productName, int quantity, BigDecimal price) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "OrderItemDTO [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

}

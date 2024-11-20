package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;

public class PriceMismatchItemDTO {
	private int productId;
	private BigDecimal requestedPrice;
	private BigDecimal currentPrice;
	
	public PriceMismatchItemDTO(int productId, BigDecimal requestedPrice, BigDecimal currentPrice) {
		this.productId = productId;
		this.requestedPrice = requestedPrice;
		this.currentPrice = currentPrice;
	}

	public int getProductId() {
		return productId;
	}

	public BigDecimal getRequestedPrice() {
		return requestedPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
}

package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;

public class ProductDTO {
	
	private int productID;
	
	private String productName;
	
	private BigDecimal productPrice;
	
	private int availableQuantity;

	public ProductDTO(int productID, String productName, BigDecimal productPrice, int availableQuantity) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.availableQuantity = availableQuantity;
	}

	public int getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

}

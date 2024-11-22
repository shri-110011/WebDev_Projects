package com.shri.ecommercebackend.validation;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CartItem {
	
	@NotNull(message = "Product ID must not be null")
	private Integer productId;
	
	@NotNull(message = "Quantity must not be null")
    @Positive(message = "Quantity must be positive")
	private Integer quantity;
	
	@NotNull(message = "Price per unit must not be null")
    @Positive(message = "Price must be positive")
	private BigDecimal pricePerUnit;
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit + "]";
	}

}

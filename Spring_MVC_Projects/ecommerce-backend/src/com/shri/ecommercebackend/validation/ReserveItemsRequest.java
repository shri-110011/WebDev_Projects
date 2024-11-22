package com.shri.ecommercebackend.validation;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReserveItemsRequest {
	
	@NotNull(message = "User ID must not be null")
	private Integer userId;
	
	@NotNull(message = "Cart items must not be null")
	@NotEmpty(message = "Cart items must not be empty")
	@Valid
	private List<CartItem> cartItems;
	
	public ReserveItemsRequest() {
		
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "ReserveItemsRequest [userId=" + userId + ", cartItems=" + cartItems + "]";
	}
	
}

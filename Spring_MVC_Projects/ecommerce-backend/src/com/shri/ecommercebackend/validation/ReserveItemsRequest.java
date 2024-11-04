package com.shri.ecommercebackend.validation;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ReserveItemsRequest {
	
	@NotNull(message = "User ID must not be null")
	private int userId;
	
	@NotNull(message = "Cart items must not be null")
	private List<CartItem> cartItems;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
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

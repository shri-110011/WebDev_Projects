package com.shri.ecommercebackend.service;

import com.shri.ecommercebackend.validation.ReserveItemsRequest;

public interface CheckoutService {
	
	public int reserveCartItems(ReserveItemsRequest reserveItemsRequest);

}

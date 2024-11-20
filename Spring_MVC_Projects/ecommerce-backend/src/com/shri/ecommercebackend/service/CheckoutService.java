package com.shri.ecommercebackend.service;

import com.shri.ecommercebackend.response.ReserveItemsResponse;
import com.shri.ecommercebackend.validation.ReserveItemsRequest;

public interface CheckoutService {
	
	public ReserveItemsResponse reserveCartItems(ReserveItemsRequest reserveItemsRequest);

}

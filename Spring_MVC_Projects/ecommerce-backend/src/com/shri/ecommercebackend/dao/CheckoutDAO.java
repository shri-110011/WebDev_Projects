package com.shri.ecommercebackend.dao;

import com.shri.ecommercebackend.validation.ReserveItemsRequest;

public interface CheckoutDAO {
	
	public int reserveCartItems(ReserveItemsRequest reserveItemsRequest);

}

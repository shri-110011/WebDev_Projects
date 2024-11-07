package com.shri.ecommercebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.ecommercebackend.dao.CheckoutDAO;
import com.shri.ecommercebackend.validation.ReserveItemsRequest;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
	private CheckoutDAO checkoutDAO;

	@Override
	public int reserveCartItems(ReserveItemsRequest reserveItemsRequest) {
		/* Loop through each item in cart and get the productIds of all items that are not there in Redis.
		 * 
		 * If there are some items that are not there in Redis fetch them from the database.
		 * 
		 * Again, loop through the cart items and check the availability for each item.
		 * 
		 * Update the availableQuantity in Redis by decrementing it if there is enough stock.
		 * 
		 * */
		return checkoutDAO.reserveCartItems(reserveItemsRequest);
	}

}

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
		return checkoutDAO.reserveCartItems(reserveItemsRequest);
	}

}

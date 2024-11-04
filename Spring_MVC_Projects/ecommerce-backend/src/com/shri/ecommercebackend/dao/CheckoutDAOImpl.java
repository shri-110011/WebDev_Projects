package com.shri.ecommercebackend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.validation.ReserveItemsRequest;

@Repository
public class CheckoutDAOImpl implements CheckoutDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int reserveCartItems(ReserveItemsRequest reserveItemsRequest) {
		System.out.println(reserveItemsRequest);
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		/* Todo: Get lock on the rows in Products table where product id matches the one of cart items.
		 * 
		 * Check if the available quantity is sufficient to meet the ask.
		 * 
		 * If not then send error message listing the products whose ask can't be fulifilled.
		 * 
		 * Else reserve items by updating the available quantity.
		 */
		return 987;
	}
	
}

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
		
		return 987;
	}
	
}

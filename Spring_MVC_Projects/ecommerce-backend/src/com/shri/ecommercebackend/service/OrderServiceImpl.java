package com.shri.ecommercebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.OrderDAO;
import com.shri.ecommercebackend.entity.Order;
import com.shri.ecommercebackend.response.PlaceOrderResponse;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;

	@Override
	@Transactional
	public PlaceOrderResponse placeOrder(int reservationId) {
		Order order = orderDAO.placeOrder(reservationId);
		System.out.println(order);
		String message = "Your order has been placed successfully!";
		return new PlaceOrderResponse(order.getOrderId(), order.getOrderStatus(), 
				order.getTotalAmount(), message);
	}

}

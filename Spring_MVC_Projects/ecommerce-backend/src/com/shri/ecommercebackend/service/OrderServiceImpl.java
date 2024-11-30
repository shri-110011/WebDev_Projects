package com.shri.ecommercebackend.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.OrderDAO;
import com.shri.ecommercebackend.entity.Order;
import com.shri.ecommercebackend.entity.OrderItem;
import com.shri.ecommercebackend.entity.OrderStatus;
import com.shri.ecommercebackend.response.CancelOrderResponse;
import com.shri.ecommercebackend.response.PlaceOrderResponse;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private RedisService redisService;

	@Override
	@Transactional
	public PlaceOrderResponse placeOrder(int reservationId) {
		Order order = orderDAO.placeOrder(reservationId);
		System.out.println(order);
		
		String message = "Your order has been placed successfully!";
		return new PlaceOrderResponse(order.getOrderId(), order.getOrderStatus(), 
				order.getTotalAmount(), message);
	}

	@Override
	@Transactional
	public CancelOrderResponse cancelOrder(int orderId) {
		BigDecimal refundAmount = orderDAO.cancelOrder(orderId);
		
		List<OrderItem> orderItems = orderDAO.getOrderItems(orderId);

		
		Map<Integer, Integer> productQuantityMap = new HashMap<>();
		
		orderItems.forEach(orderItem -> {
			productQuantityMap.put(orderItem.getOrderItemKey().getProductId(), orderItem.getQuantity());
		});
		
		redisService.executeLuaScriptToIncreaseAvailableQuantity(productQuantityMap);
		
		String message = "Your order has been cancelled successfully!";
		return new CancelOrderResponse(orderId, OrderStatus.CANCELLED, refundAmount, message);
	}

}

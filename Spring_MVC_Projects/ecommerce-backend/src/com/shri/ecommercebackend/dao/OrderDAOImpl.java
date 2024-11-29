package com.shri.ecommercebackend.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.entity.ChangeType;
import com.shri.ecommercebackend.entity.Inventory;
import com.shri.ecommercebackend.entity.InventoryOrder;
import com.shri.ecommercebackend.entity.InventoryStatus;
import com.shri.ecommercebackend.entity.Order;
import com.shri.ecommercebackend.entity.OrderItem;
import com.shri.ecommercebackend.entity.OrderStatus;
import com.shri.ecommercebackend.entity.Reservation;
import com.shri.ecommercebackend.entity.ReservationEntityStatus;
import com.shri.ecommercebackend.exception.InvalidReservationIdException;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Order placeOrder(int reservationId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Reservation reservation = currentSession.get(Reservation.class, reservationId);
		
		if(reservation == null)
			throw new InvalidReservationIdException("Reservation id: " + reservationId + " is invalid!");
		
		boolean reservationExpired = !reservation.getExpirationDateTime().isAfter(LocalDateTime.now());
		System.out.println(reservation);
		System.out.println("reservationExpired: " + reservationExpired);
		
		if(reservationExpired)
			throw new InvalidReservationIdException("Reservation id: " + reservationId + " has expired!");
		
		if(reservation.getStatus() == ReservationEntityStatus.USED)
			throw new InvalidReservationIdException("Reservation id: " + reservationId + " has already been used!");
		
		
		System.out.println("Getting inventory orders...");
		
		List<InventoryOrder> inventoryOrders = reservation.getInventoryOrders();
		
		System.out.println(inventoryOrders);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		for(InventoryOrder inventoryOrder : inventoryOrders) {
			BigDecimal priceAtPurchase = inventoryOrder.getPriceAtPurchase();
			BigDecimal quantity = BigDecimal.valueOf(inventoryOrder.getQuantity());
			totalAmount = priceAtPurchase.multiply(quantity).add(totalAmount);
		};
		
		Order order = new Order();
		order.setUserId(reservation.getUserId());
		order.setTotalAmount(totalAmount);
		order.setOrderStatus(OrderStatus.CONFIRMED);
		
		int orderId = (int)currentSession.save(order);
		
		System.out.println("orderId: " + orderId);
		
		inventoryOrders.stream().forEach(inventoryOrder -> {
			Inventory inventory = inventoryOrder.getInventory();
			inventory.setChangeType(ChangeType.SALE);
			inventory.setStatus(InventoryStatus.COMPLETED);
			inventory.setStatusChangeDatetime(LocalDateTime.now());
			
			OrderItem orderItem = new OrderItem(orderId, inventoryOrder.getProductId(), 
					inventoryOrder.getQuantity(), inventoryOrder.getPriceAtPurchase());
			System.out.println(orderItem);
			System.out.println(inventoryOrder.getInventory());
			// Synchronize the relationship
			order.addInventoryOrder(inventoryOrder);
			order.addOrderItem(orderItem);
		});
		
		System.out.println(order.getOrderItems());
		System.out.println(order.getInventoryOrders());
		reservation.setStatus(ReservationEntityStatus.USED);
		currentSession.save(reservation);
		
		return order;
	}

}

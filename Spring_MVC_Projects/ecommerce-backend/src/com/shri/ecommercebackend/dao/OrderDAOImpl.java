package com.shri.ecommercebackend.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.dto.CancellationDetailsDTO;
import com.shri.ecommercebackend.dto.OrderDetailsDTO;
import com.shri.ecommercebackend.dto.OrderItemDTO;
import com.shri.ecommercebackend.entity.InventoryEventLog;
import com.shri.ecommercebackend.entity.InventoryEventOrderReservationLink;
import com.shri.ecommercebackend.entity.InventoryEventStatus;
import com.shri.ecommercebackend.entity.InventoryEventType;
import com.shri.ecommercebackend.entity.Order;
import com.shri.ecommercebackend.entity.OrderItem;
import com.shri.ecommercebackend.entity.OrderStatus;
import com.shri.ecommercebackend.entity.Reservation;
import com.shri.ecommercebackend.entity.ReservationEntityStatus;
import com.shri.ecommercebackend.entity.ReturnEventLog;
import com.shri.ecommercebackend.entity.ReturnItem;
import com.shri.ecommercebackend.entity.ReturnStatus;
import com.shri.ecommercebackend.exception.InvalidOrderIdException;
import com.shri.ecommercebackend.exception.InvalidReservationIdException;
import com.shri.ecommercebackend.response.OrderStatusResponse;

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
		
		System.out.println("Getting inventoryOrderReservationLinks...");
		
		List<InventoryEventOrderReservationLink> inventoryOrderReservationLinks = 
				reservation.getInventoryEventOrderReservationLinks();
		
		System.out.println(inventoryOrderReservationLinks);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		for(InventoryEventOrderReservationLink inventoryOrderReservationLink : inventoryOrderReservationLinks) {
			BigDecimal priceAtPurchase = inventoryOrderReservationLink.getPriceAtPurchase();
			BigDecimal quantity = BigDecimal.valueOf(inventoryOrderReservationLink.getQuantity());
			totalAmount = priceAtPurchase.multiply(quantity).add(totalAmount);
		};
		
		Order order = new Order();
		order.setUserId(reservation.getUserId());
		order.setTotalAmount(totalAmount);
		order.setOrderStatus(OrderStatus.CONFIRMED);
		order.setRefundedAmount(BigDecimal.ZERO);
		
		int orderId = (int)currentSession.save(order);
		
		System.out.println("orderId: " + orderId);
		
		inventoryOrderReservationLinks.stream().forEach(inventoryOrderReservationLink -> {
			InventoryEventLog inventoryEventLog = inventoryOrderReservationLink.getInventoryEventLog();
			inventoryEventLog.setChangeType(InventoryEventType.SALE);
			inventoryEventLog.setStatus(InventoryEventStatus.COMPLETED);
			inventoryEventLog.setStatusChangeDatetime(LocalDateTime.now());
			
			inventoryOrderReservationLink.setOrderId(orderId);
			
			OrderItem orderItem = new OrderItem(orderId, inventoryOrderReservationLink.getProductId(), 
					inventoryOrderReservationLink.getQuantity(), 
					inventoryOrderReservationLink.getPriceAtPurchase());
			
			System.out.println(orderItem);
			System.out.println(inventoryOrderReservationLink.getInventoryEventLog());
			
			// Synchronize the relationship
			order.addInventoryEventOrderReservationLink(inventoryOrderReservationLink);
			order.addOrderItem(orderItem);
		});
		
		System.out.println(order.getOrderItems());
		System.out.println(order.getInventoryEventOrderReservationLinks());
		reservation.setStatus(ReservationEntityStatus.USED);
		currentSession.save(reservation);
		
		return order;
	}

	@Override
	public BigDecimal cancelOrder(int orderId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Order order = currentSession.get(Order.class, orderId);
		
		if(order == null)
			throw new InvalidOrderIdException("Order id: " + orderId + " is invalid!");
		
		if(order.getOrderStatus() == OrderStatus.CANCELLED)
			throw new InvalidReservationIdException("Order id: " + orderId + " has already been cancelled!");
		
		ReturnEventLog returnEventLog = new ReturnEventLog();
		returnEventLog.setOrderId(orderId);
		returnEventLog.setStatus(ReturnStatus.COMPLETED);
		returnEventLog.setStatusChangeDatetime(LocalDateTime.now());
		
		int returnId = (Integer)currentSession.save(returnEventLog);
		
		System.out.println("returnId: " + returnId);
		
		System.out.println("Getting inventoryOrderReservationLinks...");
		
		List<InventoryEventOrderReservationLink> inventoryEventOrderReservationLinks = 
				order.getInventoryEventOrderReservationLinks();
				
		for(InventoryEventOrderReservationLink inventoryEventOrderReservationLink : 
			inventoryEventOrderReservationLinks) {
			returnEventLog.addReturnItem(
					new ReturnItem(inventoryEventOrderReservationLink.getId(), returnId,
							inventoryEventOrderReservationLink.getProductId(), 
							inventoryEventOrderReservationLink.getQuantity(), "User Request")
			);
		}
		
		order.setOrderStatus(OrderStatus.CANCELLED);
		order.setOrderCancellationDateTime(LocalDateTime.now());
		order.setRefundedAmount(order.getTotalAmount());
		
		currentSession.save(order);
		
		return order.getRefundedAmount();
	}
	
	@Override
	public List<OrderItem> getOrderItems(int orderId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Order order = currentSession.get(Order.class, orderId);
		
		System.out.println("Getting inventoryOrderReservationLinks...");
		
		return order.getOrderItems();
	}

	@Override
	public OrderStatusResponse getOrderStatus(int orderId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Order order = currentSession.get(Order.class, orderId);
		
		if(order == null)
			throw new InvalidOrderIdException("Order id: " + orderId + " is invalid!");
		
		List<OrderItem> orderItems = order.getOrderItems();
		
		List<Integer> productIds = orderItems.stream()
				.map(orderItem -> orderItem.getOrderItemKey().getProductId())
				.toList();
		
		Query<String> query = currentSession.createQuery(
				"select new java.lang.String(p.productName) from Product p where p.productId in :productIds",
				String.class
				);
		query.setParameter("productIds", productIds);
		
		List<String> productNames = query.getResultList();
		
		List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
		
		int i = 0;
		for(OrderItem orderItem : orderItems) {
			int productId = orderItem.getOrderItemKey().getProductId();
			orderItemDTOs.add(new OrderItemDTO(productId, productNames.get(i), 
					orderItem.getQuantity(), orderItem.getPriceAtPurchase()));
		}
		
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderItemDTOs, 
				order.getOrderCreationDateTime(), order.getTotalAmount());
		
		CancellationDetailsDTO cancellationDetailsDTO = null;
		
		if(order.getOrderStatus() == OrderStatus.CANCELLED) {
			cancellationDetailsDTO = new CancellationDetailsDTO(order.getOrderCancellationDateTime(), 
					order.getRefundedAmount());
		}
		
		return new OrderStatusResponse(orderId, order.getOrderStatus(), cancellationDetailsDTO, 
				orderDetailsDTO, System.currentTimeMillis());
	}
	
}

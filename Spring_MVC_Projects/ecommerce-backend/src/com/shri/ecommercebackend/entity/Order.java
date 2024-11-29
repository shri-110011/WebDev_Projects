package com.shri.ecommercebackend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "order_creation_datetime", insertable = false, updatable = false)
	private LocalDateTime orderCreationDateTime;

	@Column(name = "order_cancellation_datetime", insertable = false, updatable = false)
	private LocalDateTime orderCancellationDateTime;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderItem> orderItems;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<InventoryEventOrderReservationLink> inventoryEventOrderReservationLinks;

	public Order() {

	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus.toString();
	}

	public LocalDateTime getOrderCreationDateTime() {
		return orderCreationDateTime;
	}

	public void setOrderCreationDateTime(LocalDateTime orderCreationDateTime) {
		this.orderCreationDateTime = orderCreationDateTime;
	}

	public LocalDateTime getOrderCancellationDateTime() {
		return orderCancellationDateTime;
	}

	public void setOrderCancellationDateTime(LocalDateTime orderCancellationDateTime) {
		this.orderCancellationDateTime = orderCancellationDateTime;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<InventoryEventOrderReservationLink> getInventoryEventOrderReservationLinks() {
		return inventoryEventOrderReservationLinks;
	}

	public void setInventoryEventOrderReservationLinks(
			List<InventoryEventOrderReservationLink> inventoryEventOrderReservationLinks) {
		this.inventoryEventOrderReservationLinks = inventoryEventOrderReservationLinks;
	}

	public void addOrderItem(OrderItem orderItem) {
		if (orderItems == null) {
			orderItems = new ArrayList<>();
		}
		orderItems.add(orderItem);
	}

	public void addInventoryEventOrderReservationLink(
			InventoryEventOrderReservationLink inventoryEventOrderReservationLink) {
		if (inventoryEventOrderReservationLinks == null) {
			inventoryEventOrderReservationLinks = new ArrayList<>();
		}
		inventoryEventOrderReservationLinks.add(inventoryEventOrderReservationLink);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", orderStatus="
				+ orderStatus + "]";
	}

}

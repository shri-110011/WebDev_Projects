package com.shri.ecommercebackend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@EmbeddedId
	private OrderItemKey orderItemKey;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price_at_purchase")
	private BigDecimal priceAtPurchase;
	
	public OrderItem() {
	
	}
		
	public OrderItem(int orderId, int productId, int quantity, BigDecimal priceAtPurchase) {
		this.orderItemKey = new OrderItemKey(orderId, productId);
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
	}

	public OrderItemKey getOrderItemKey() {
		return orderItemKey;
	}

	public void setOrderItemKey(OrderItemKey orderItemKey) {
		this.orderItemKey = orderItemKey;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceAtPurchase() {
		return priceAtPurchase;
	}

	public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemKey=" + orderItemKey + ", quantity=" + quantity + ", priceAtPurchase="
				+ priceAtPurchase + "]";
	}

}

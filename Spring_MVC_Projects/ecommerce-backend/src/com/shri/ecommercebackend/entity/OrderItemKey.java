package com.shri.ecommercebackend.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderItemKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "product_id")
	private int productId;

	public OrderItemKey() {
	
	}

	public OrderItemKey(int orderId, int productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemKey other = (OrderItemKey) obj;
		return orderId == other.orderId && productId == other.productId;
	}

	@Override
	public String toString() {
		return "OrderItemKey [orderId=" + orderId + ", productId=" + productId + "]";
	}

}

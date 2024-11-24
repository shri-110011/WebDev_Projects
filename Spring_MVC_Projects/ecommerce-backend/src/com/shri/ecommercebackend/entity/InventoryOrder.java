package com.shri.ecommercebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_orders")
public class InventoryOrder {

	@Id
	@Column(name = "inventory_id")
	private int inventoryId;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	public InventoryOrder() {
	
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	@Override
	public String toString() {
		return "InventoryOrder [inventoryId=" + inventoryId + ", orderId=" + orderId + ", reservation=" + reservation
				+ "]";
	}
	
}

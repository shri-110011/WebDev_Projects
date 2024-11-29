package com.shri.ecommercebackend.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_events_orders_reservations_link")
public class InventoryEventOrderReservationLink {

	@Id
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "inventory_event_id")
	private InventoryEventLog inventoryEventLog;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price_at_purchase")
	private BigDecimal priceAtPurchase;

	public InventoryEventOrderReservationLink() {
	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InventoryEventLog getInventoryEventLog() {
		return inventoryEventLog;
	}

	public void setInventoryEventLog(InventoryEventLog inventoryEventLog) {
		this.inventoryEventLog = inventoryEventLog;
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
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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
		return "InventoryEventOrderReservationLink [id=" + id + ", inventoryEventLog=" + inventoryEventLog + ", orderId=" + orderId
				+ ", reservation=" + reservation + ", productId=" + productId + ", quantity=" + quantity
				+ ", priceAtPurchase=" + priceAtPurchase + "]";
	}
	
}

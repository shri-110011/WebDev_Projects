package com.shri.ecommercebackend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private int reservationId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "creation_datetime", insertable = false, updatable = false)
	private LocalDateTime creationDateTime;

	@Column(name = "expiration_datetime", insertable = false, updatable = false)
	private LocalDateTime expirationDateTime;
	
	@Column(name = "status")
	private String status;
	
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private List<InventoryOrder> inventoryOrders;

	public Reservation() {
	
	}

	public Reservation(int userId, ReservationEntityStatus status) {
		this.userId = userId;
		this.status = status.name();
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	public ReservationEntityStatus getStatus() {
		return ReservationEntityStatus.valueOf(status);
	}

	public void setStatus(ReservationEntityStatus status) {
		this.status = status.name();
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<InventoryOrder> getInventoryOrders() {
		return inventoryOrders;
	}

	public void setInventoryOrders(List<InventoryOrder> inventoryOrders) {
		this.inventoryOrders = inventoryOrders;
	}
	
	public void addInventoryOrder(InventoryOrder inventoryOrder) {
		if(inventoryOrders == null) {
			inventoryOrders = new ArrayList<>();
		}
		inventoryOrders.add(inventoryOrder);
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", userId=" + userId + ", creationDateTime="
				+ creationDateTime + ", expirationDateTime=" + expirationDateTime + ", status=" + status + "]";
	}

}

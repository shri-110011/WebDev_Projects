package com.shri.ecommercebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name = "status")
	private String status;

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

	public ReservationEntityStatus getStatus() {
		return ReservationEntityStatus.valueOf(status);
	}

	public void setStatus(ReservationEntityStatus status) {
		this.status = status.name();
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", userId=" + userId + ", status=" + status + "]";
	}

}

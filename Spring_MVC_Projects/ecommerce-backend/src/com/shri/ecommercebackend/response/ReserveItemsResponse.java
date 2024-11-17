package com.shri.ecommercebackend.response;


public class ReserveItemsResponse {
	private int reservationId;

	public ReserveItemsResponse(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getReservationId() {
		return reservationId;
	}
}

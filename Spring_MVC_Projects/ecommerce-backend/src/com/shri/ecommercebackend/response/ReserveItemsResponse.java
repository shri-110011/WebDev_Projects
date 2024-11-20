package com.shri.ecommercebackend.response;

import java.util.List;

import com.shri.ecommercebackend.dto.InvalidItemDTO;
import com.shri.ecommercebackend.dto.PriceMismatchItemDTO;
import com.shri.ecommercebackend.dto.ReservedItemDTO;
import com.shri.ecommercebackend.dto.UnavailableItemDTO;

public class ReserveItemsResponse {
	private int reservationId;
	private ReservationStatus reservationStatus;
	private List<ReservedItemDTO> reservedItems;
	private List<UnavailableItemDTO> unavailableItems;
	private List<InvalidItemDTO> invalidItems;
	private List<PriceMismatchItemDTO> priceMismatchItems;
	
	public ReserveItemsResponse(int reservationId, ReservationStatus reservationStatus, List<ReservedItemDTO> reservedItems,
			List<UnavailableItemDTO> unavailableItems, List<InvalidItemDTO> invalidItems,
			List<PriceMismatchItemDTO> priceMismatchItems) {
		this.reservationId = reservationId;
		this.reservationStatus = reservationStatus;
		this.reservedItems = reservedItems;
		this.unavailableItems = unavailableItems;
		this.invalidItems = invalidItems;
		this.priceMismatchItems = priceMismatchItems;
	}

	public int getReservationId() {
		return reservationId;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public List<ReservedItemDTO> getReservedItems() {
		return reservedItems;
	}

	public List<UnavailableItemDTO> getUnavailableItems() {
		return unavailableItems;
	}

	public List<InvalidItemDTO> getInvalidItems() {
		return invalidItems;
	}

	public List<PriceMismatchItemDTO> getPriceMismatchItems() {
		return priceMismatchItems;
	}
}
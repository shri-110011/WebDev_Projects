package com.shri.ecommercebackend.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shri.ecommercebackend.response.ReservationStatus;
import com.shri.ecommercebackend.response.ReserveItemsResponse;
import com.shri.ecommercebackend.service.CheckoutService;
import com.shri.ecommercebackend.validation.ReserveItemsRequest;

@RestController
@RequestMapping("/api")
public class CheckoutRestController {
	
	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping(path = "/v1/reserve-items")
	public ResponseEntity<ReserveItemsResponse> reserveCartItems(@RequestBody @Valid ReserveItemsRequest 
			reserveItemsRequest) {
		ReserveItemsResponse reserveItemsResponse = checkoutService.reserveCartItems(reserveItemsRequest);
		if(reserveItemsResponse.getReservationStatus() == ReservationStatus.Failed) {
			return new ResponseEntity<ReserveItemsResponse>(reserveItemsResponse, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(reserveItemsResponse);
	}

}

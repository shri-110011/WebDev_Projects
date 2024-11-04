package com.shri.ecommercebackend.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shri.ecommercebackend.response.ReserveItemsResponse;
import com.shri.ecommercebackend.service.CheckoutService;
import com.shri.ecommercebackend.validation.ReserveItemsRequest;

@RestController
@RequestMapping("/api")
public class CheckoutRestController {
	
	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping(path = "/v1/reserve-items")
	public ResponseEntity<ReserveItemsResponse> reserveCartItems(@Valid @RequestBody ReserveItemsRequest reserveItemsRequest) {
		int resevationId = checkoutService.reserveCartItems(reserveItemsRequest);
		ReserveItemsResponse reserveItemsResponse = new ReserveItemsResponse(resevationId);
		return ResponseEntity.ok(reserveItemsResponse);
	}

}

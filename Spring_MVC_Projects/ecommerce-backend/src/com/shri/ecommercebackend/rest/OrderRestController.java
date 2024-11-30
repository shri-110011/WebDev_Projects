package com.shri.ecommercebackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shri.ecommercebackend.exception.ErrorResponse;
import com.shri.ecommercebackend.exception.InvalidOrderIdException;
import com.shri.ecommercebackend.exception.InvalidReservationIdException;
import com.shri.ecommercebackend.response.CancelOrderResponse;
import com.shri.ecommercebackend.response.PlaceOrderResponse;
import com.shri.ecommercebackend.service.OrderService;

@RestController
@RequestMapping(path  = "/api")
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(path = "/v1/place-order")
	public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestParam("reservationId") String reservationIdParam) {
		int reservationId = Integer.parseInt(reservationIdParam);
		if(reservationId <= 0) throw new InvalidReservationIdException("Reservation id: " + reservationId + " is invalid!");
		PlaceOrderResponse placeOrderResponse = orderService.placeOrder(reservationId);
		return ResponseEntity.ok(placeOrderResponse);
	}
	
	@PostMapping(path = "/v1/cancel-order")
	public ResponseEntity<CancelOrderResponse> cancelOrder(@RequestParam("orderId") int orderIdParam) {
//		int orderId = Integer.parseInt(orderIdParam);
		if(orderIdParam <= 0) throw new InvalidReservationIdException("Order id: " + orderIdParam + " is invalid!");
		CancelOrderResponse cancelOrderResponse = orderService.cancelOrder(orderIdParam);
		return ResponseEntity.ok(cancelOrderResponse);
	}

	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(InvalidReservationIdException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(InvalidOrderIdException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

}

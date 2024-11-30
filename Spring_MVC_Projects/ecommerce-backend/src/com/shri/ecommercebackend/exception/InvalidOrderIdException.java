package com.shri.ecommercebackend.exception;

public class InvalidOrderIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidOrderIdException(String message) {
		super(message);
	}
		
}

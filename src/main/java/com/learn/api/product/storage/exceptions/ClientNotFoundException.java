package com.learn.api.product.storage.exceptions;

public class ClientNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(String message) {
		super(message);
	}
	
}

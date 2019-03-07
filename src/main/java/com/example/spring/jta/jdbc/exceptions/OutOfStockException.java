package com.example.spring.jta.jdbc.exceptions;

public class OutOfStockException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	public OutOfStockException(String msg) {
		this.message = msg;
	}
	public String getMessage() {
		return message;
	}
}

package com.revature.exception;

public class InvalidLoginException extends RuntimeException {

	private static final long serialVersionUID = -5277122742412890255L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLoginException(String message) {
		super("The email or password entered is invalid");
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
	}
	
}

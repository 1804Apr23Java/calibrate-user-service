package com.revature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5414309085726778617L;

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNotFoundException(String message) {
		super(message);
	}

	public AccountNotFoundException(Throwable cause) {
		super(cause);
	}

}

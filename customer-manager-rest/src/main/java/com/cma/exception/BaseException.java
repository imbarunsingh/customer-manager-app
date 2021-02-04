package com.cma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.cma.constants.CommonConstants;


public class BaseException extends ResponseStatusException {

	private static final long serialVersionUID = 8971474523584631033L;

	public BaseException() {
		super(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public BaseException(HttpStatus status) {
		super(status);
	}

	public BaseException(HttpStatus status, String reason) {
		super(status, reason);
	}

	public BaseException(HttpStatus status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public BaseException(Throwable cause) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, CommonConstants.DEFAULT_ERROR_MSG, cause);
	}

	public BaseException(String reason, Throwable cause) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, reason, cause);
	}
}

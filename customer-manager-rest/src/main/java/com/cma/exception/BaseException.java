package com.cma.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception implements Serializable {

	private static final long serialVersionUID = 6059118552627710782L;

	private HttpStatus httpStatus;
	private String message;

	public BaseException() {
		
	}

	public BaseException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public BaseException(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

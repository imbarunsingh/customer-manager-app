package com.cma.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionResponseBuilder {

	private int status;
	private String error;
	private String message;
	private String path;

	public ExceptionResponseBuilder status(int status) {
		this.status = status;
		return this;
	}

	public ExceptionResponseBuilder status(HttpStatus status) {
		this.status = status.value();

		if (status.isError()) {
			this.error = status.getReasonPhrase();
		}
		return this;
	}

	public ExceptionResponseBuilder error(String error) {
		this.error = error;
		return this;
	}

	public ExceptionResponseBuilder exception(ResponseStatusException exception) {
		HttpStatus status = exception.getStatus();
		this.status = status.value();

		if (StringUtils.isNotBlank(exception.getReason())) {
			this.message = exception.getReason();
		}
		if (status.isError()) {
			this.error = status.getReasonPhrase();
		}
		return this;
	}

	public ExceptionResponseBuilder message(String message) {
		this.message = message;
		return this;
	}

	public ExceptionResponseBuilder path(String path) {
		this.path = path;
		return this;
	}

	public ExceptionResponse build() {
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(status);
		response.setError(error);
		response.setMessage(message);
		response.setPath(path);
		return response;
	}

	public ResponseEntity<ExceptionResponse> entity() {
		return ResponseEntity.status(status).headers(HttpHeaders.EMPTY).body(build());
	}

	public String json() {
		return build().toJson();
	}

}

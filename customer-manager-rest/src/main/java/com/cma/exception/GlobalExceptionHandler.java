package com.cma.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cma.constants.CommonConstants;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResponseStatusException.class)
	ResponseEntity<?> handleStatusException(ResponseStatusException ex, WebRequest request) {
		System.out.println("step 1");
		logger.error(ex.getReason(), ex);
		return handleResponseStatusException(ex, request);
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<?> defaultExceptionHandler(Exception ex, WebRequest request) {
		System.out.println("step 2");
		logger.error(ex.getLocalizedMessage(), ex);
		return handleAllException(ex, request);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ResponseEntity<?> responseEntity;

		if (!status.isError()) {
			responseEntity = handleStatusException(ex, status, request);
		} else if (INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute("javax.servlet.error.exception", ex, 0);
			responseEntity = handleAllException(ex, request);
		} else {
			responseEntity = handleAllException(ex, request);
		}
		return (ResponseEntity<Object>) responseEntity;
	}

	protected ResponseEntity<ExceptionResponse> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
		return ExceptionResponse.builder()
								.exception(ex)
								.path(getPath(request))
								.entity();
	}

	protected ResponseEntity<ExceptionResponse> handleStatusException(Exception ex, HttpStatus status, WebRequest request) {
		return ExceptionResponse.builder()
								.status(status)
								.message("Execution halted")
								.path(getPath(request))
								.entity();
	}

	protected ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
		return ExceptionResponse.builder()
								.status(INTERNAL_SERVER_ERROR)
								.message(CommonConstants.DEFAULT_ERROR_MSG)
								.path(getPath(request)).entity();
	}

	private String getPath(WebRequest request) {
		return request.getDescription(false).substring(4);
	}
}

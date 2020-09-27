package com.cma.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cma.constants.CommonConstants;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ServletRequestBindingException.class)
	public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request) {
		BaseException baseException = new BaseException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(baseException, baseException.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> defaultErrorHandler(Exception exception, WebRequest request) {
		String errorMessage;
		HttpStatus httpStatus;
		if (exception instanceof BaseException) {
			BaseException baseException = (BaseException) exception;
			httpStatus = baseException.getHttpStatus();
			errorMessage = baseException.getMessage();
			if (null == httpStatus) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMessage = CommonConstants.DEFAULT_ERROR_MSG;
			} else if (null == errorMessage || errorMessage.isEmpty()) {
				errorMessage = CommonConstants.DEFAULT_ERROR_MSG;
			}
		} else {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			errorMessage = CommonConstants.DEFAULT_ERROR_MSG;
		}
		String referenceId = MDC.get("UNIQUE_ID");
		String reference = referenceId != null ? "[Reference:" + referenceId + "]\n" : "";

		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(reference + errorMessage, details);
		return new ResponseEntity<Object>(exceptionResponse, httpStatus);
	}

}
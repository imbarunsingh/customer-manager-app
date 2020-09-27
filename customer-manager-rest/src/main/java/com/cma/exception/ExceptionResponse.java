package com.cma.exception;

import java.util.List;

public class ExceptionResponse {

	//General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;

	public ExceptionResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	

}
package com.paypal.bfs.test.exception.handler;

import java.util.Map;
import java.util.Set;

public class ErrorResponse {
	
	private String error;
	
	private Map<String, Set<String>> message;
	
	public ErrorResponse(String error, Map<String, Set<String>> message){
		this.error = error;
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Map<String, Set<String>> getMessage() {
		return message;
	}

	public void setMessage(Map<String, Set<String>> message) {
		this.message = message;
	}
	
	

}

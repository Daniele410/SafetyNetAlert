package com.safetyNetAlert.safetyNetAlert.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import exception.PersonNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException ex) {
		String errorMessage = ex.getMessage();
		LOGGER.error("Response : 404 {}", errorMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	
	

}

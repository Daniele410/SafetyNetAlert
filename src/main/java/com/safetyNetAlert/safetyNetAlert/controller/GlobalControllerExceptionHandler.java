package com.safetyNetAlert.safetyNetAlert.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import exception.AlertControllerNotFoundException;
import exception.FirestationNotFoundException;
import exception.MedicalRecordNotFoundException;
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
	
	@ExceptionHandler(FirestationNotFoundException.class)
	public ResponseEntity<String> handleFirestationNotFoundException(FirestationNotFoundException ex) {
		String errorMessage = ex.getMessage();
		LOGGER.error("Response : 404 {}", errorMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	
	@ExceptionHandler(MedicalRecordNotFoundException.class)
	public ResponseEntity<String> handleMedicalRecordNotFoundException(MedicalRecordNotFoundException ex) {
		String errorMessage = ex.getMessage();
		LOGGER.error("Response : 404 {}", errorMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	
	@ExceptionHandler(AlertControllerNotFoundException.class)
	public ResponseEntity<String> handleAlertControllerNotFoundException(AlertControllerNotFoundException ex) {
		String errorMessage = ex.getMessage();
		LOGGER.error("Response : 404 {}", errorMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

}

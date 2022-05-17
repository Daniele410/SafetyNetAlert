package com.safetyNetAlert.safetyNetAlert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {
	
	@GetMapping(value = "/toto")
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<>("hello world", HttpStatus.OK);
	}

}

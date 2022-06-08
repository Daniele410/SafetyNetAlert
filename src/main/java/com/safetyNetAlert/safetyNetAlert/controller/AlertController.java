package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.service.IAlertService;

@RestController
public class AlertController {
	
	static final Logger logger = LogManager.getLogger(PersonController.class);
	
	@Autowired
	private IAlertService alertService;
	
	
	
	@GetMapping(value = "/toto")
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<>("hello world", HttpStatus.OK);
	}
	
	@GetMapping(value = "/communityEmail")
	public ResponseEntity<List<String>> getCommunityEmail(@RequestParam String city) {
		logger.info("GET /communityEmail called");
		return new ResponseEntity<>(alertService.getCommunityEmail(city), HttpStatus.OK);
	}
	
	@GetMapping(value = "/personInfo")
	public ResponseEntity<List<PersonInfoDto>> listPersonsInfo(@RequestParam String firstName, @RequestParam String lastName ) {
		logger.info("GET /personInfo called");
		return new ResponseEntity<>(alertService.getPersonInfo(firstName, lastName), HttpStatus.OK);
	}

}

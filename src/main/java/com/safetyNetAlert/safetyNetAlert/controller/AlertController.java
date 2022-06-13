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
		if (city.isBlank()) {
			logger.error("email is blank");
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		} else {
		logger.info("List of communityEmail generated");
		return new ResponseEntity<>(alertService.getCommunityEmail(city), HttpStatus.OK);
	}
		}

//	@GetMapping(value = "/personInfo")
//	public ResponseEntity<PersonInfoDto> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {
//		if (firstName.isBlank() || lastName.isBlank()) {
//			logger.error("Firstname or Lastname blank");
//			return new ResponseEntity<PersonInfoDto>(HttpStatus.NOT_FOUND);
//		} else {
//			logger.info("List of Person generated");
//			return new ResponseEntity<PersonInfoDto>((PersonInfoDto) alertService.getPersonInfo(firstName, lastName), HttpStatus.OK);
//		}

	@GetMapping("/personInfo")
	public List<PersonInfoDto> listPersonInfo(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName) {
		logger.debug("call safetyNet controller - personInfo");
		return this.alertService.getPersonInfo(firstName, lastName);
	
	}
}

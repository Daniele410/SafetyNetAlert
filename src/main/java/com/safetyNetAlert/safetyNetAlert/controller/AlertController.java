package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.FloodDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonAtAddressDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonByFirestationDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.service.IAlertService;

import exception.PersonNotFoundException;

@RestController
public class AlertController {

	static final Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private IAlertService alertService;

	// Url= http://localhost:8080/communityEmail?city=<city>
	@GetMapping(value = "/communityEmail")
	public ResponseEntity<List<String>> getCommunityEmail(@RequestParam String city) throws Exception {
		logger.debug("GET Request on /communityEmail with city {}", city);
		logger.info("List of communityEmail generated ");
		return new ResponseEntity<>(alertService.getCommunityEmail(city), HttpStatus.OK);

	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@GetMapping(value = "/personInfo")
	public ResponseEntity<List<PersonInfoDto>> getPersonInfo(@RequestParam String lastName, String firstName)
			throws Exception {
		logger.debug("GET Request on /personInfo with firstName {} and lastName {}", lastName, firstName);
		logger.info("List of Person generated - Success");
		return new ResponseEntity<List<PersonInfoDto>>(alertService.getPersonInfo(lastName), HttpStatus.OK);

	}

	// http://localhost:8080/childAlert?address=<address>
	@GetMapping(value = "/childAlert")
	public ResponseEntity<List<ChildDto>> getChildDto(@RequestParam String address) throws PersonNotFoundException {
		logger.info("List of childAddress generated");
		return new ResponseEntity<List<ChildDto>>(alertService.getChildDto(address), HttpStatus.OK);

	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@GetMapping(value = "/phoneAlert")
	public ResponseEntity<Set<String>> getPersonsPhoneNumberByStation(@RequestParam String firestation) throws PersonNotFoundException {

		logger.info("List of PhoneAlert By Station number generated");
		return new ResponseEntity<Set<String>>(alertService.getPersonsPhoneNumberByStation(firestation), HttpStatus.OK);

	}

	// http://localhost:8080/fire?address=<address>
	@GetMapping(value = "/fire")
	public ResponseEntity<List<PersonAtAddressDto>> getPersonsByAddressFromListOfStationNumber(
			@RequestParam String address) throws PersonNotFoundException {

		logger.info("List of persons By Station address generated");
		return new ResponseEntity<List<PersonAtAddressDto>>(
				alertService.getPersonsByAddressFromListOfStationNumber(address), HttpStatus.OK);

	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@GetMapping(value = "/flood/stations")
	public ResponseEntity<List<FloodDto>> getPersonsBySameAddress(@RequestParam(value = "stations") String station) throws PersonNotFoundException {

		logger.info("List of persons By Station address generated");
		return new ResponseEntity<List<FloodDto>>(alertService.getPersonsBySameAddress(station), HttpStatus.OK);

	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	@GetMapping(value = "/firestations")
	public ResponseEntity<PersonByFirestationDto> getPersonsCoveredByStation(
			@RequestParam(value = "stationNumber") String stationNumber) throws PersonNotFoundException {

		logger.info("List of persons By Station address generated");
		return new ResponseEntity<PersonByFirestationDto>(alertService.getPersonsCoveredByStation(stationNumber),
				HttpStatus.OK);

	}

}

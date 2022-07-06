package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.service.IPersonService;

@RestController
public class PersonController {

	static final Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private IPersonService personService;

	@GetMapping(value = "/person")
	public ResponseEntity<List<Person>> getAllPersons() {
		logger.info("GET /person called");

		return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
	}

	// Add a new person
	@PostMapping(value = "/person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) throws Exception {
		logger.debug("Person UPDATE request on: " + person.getFirstName() + " " + person.getLastName());

		personService.addPerson(person);
		logger.info("POST /person called");
		return new ResponseEntity<>(person, HttpStatus.CREATED);

	}

	// Update a stored person
	@PutMapping(value = "/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws Exception {

		logger.info("Person PUT request - SUCCESS");

		return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);

	}

	// The function receives a DELETE request, delete the Person with the specified
	// FirstName and LastName.
	@DeleteMapping(value = "/person")
	public ResponseEntity<Person> deletePerson(@RequestBody Person person) throws Exception {
		logger.debug("Person DELETE request on : " + person);

		logger.info("Person DELETE request - SUCCESS");
		return new ResponseEntity<>(personService.deletePerson(person), HttpStatus.OK);

	}

}

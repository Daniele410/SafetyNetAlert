package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping(value = "/persons/getAll")
	public ResponseEntity<List<Person>> getAllPersons() {
				
		return new ResponseEntity<>(personRepository.getAllPersons(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		personRepository.addPerson(person);
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}
	
}

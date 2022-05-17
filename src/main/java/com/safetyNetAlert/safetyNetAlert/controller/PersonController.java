package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping(value = "/person/getAll")
	public ResponseEntity<List<Person>> getAllPersons() {
		
		return new ResponseEntity<>(personRepository.getAllPersons(), HttpStatus.OK);
	}
}

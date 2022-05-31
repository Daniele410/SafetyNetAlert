package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;
import com.safetyNetAlert.safetyNetAlert.service.PersonServiceImpl;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	
	@GetMapping(value = "/person")
	public ResponseEntity<List<Person>> getAllPersons() {
				
		return new ResponseEntity<>(personRepository.getAllPersons(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/person/{firstName}")
	public ResponseEntity<List<Person>> getAllPersons(@PathVariable("firstName") String firstName) {
		return new ResponseEntity<>(personRepository.getAllPersons(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		personRepository.addPerson(person);
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}
	
	 @PutMapping(value = "/person")
	    public ResponseEntity<Person> updatePerson(@PathVariable("firstName") String personFirstName, @RequestBody Person person) {
	        personServiceImpl.updatePerson(personFirstName, person);
	        return new ResponseEntity<>(personServiceImpl.getPersonByFirstName(personFirstName), HttpStatus.OK);
	    }
	    //The function receives a DELETE request, delete the Person with the specified FirstName.
	    @DeleteMapping({"person/firstName"})
	    public ResponseEntity<Person> deleteTodo(@PathVariable("firstName") String firstName) {
	        personServiceImpl.deletePerson(firstName);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	
	
	
}

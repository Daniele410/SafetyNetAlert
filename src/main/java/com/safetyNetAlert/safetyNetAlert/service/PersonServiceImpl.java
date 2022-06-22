package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

@Service
public class PersonServiceImpl implements IPersonService {

	static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

	@Autowired
	PersonRepository personRepository;
	
	
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		personRepository.getAllPersons().forEach(persons::add);
		logger.info("Getting all persons");
		return persons;
	}

	
	@Override
	public List<Person> getPersonByLastName(String lastName) {
		return personRepository.findByLastName(lastName);
	}


	@Override
	public Person getPersonByEmail(String email) {
		return this.getPersonByEmail(email);
	}

	public void updatePerson(String personFirstName, Person person) {

	}

	public List<Person> deletePerson(String firstName) {
		return null;

	}

	@Override
	public Person deletePerson(Person person) {
		//rechercher si la personne existe
		//si elle n'existe pas alors on throw une exception, PersonNotFoundException
		personRepository.deletePerson(person);
		logger.info("Deleting the person with keyname : " + person);
		return null;
	}

	@Override
	public Person updatePerson(Person person) {
		// Vérifier si la Person existe

		personRepository.updatePerson(person);
		logger.info("Saving the person : " + person);
		return this.updatePerson(person);
	}

	@Override
	public Person addPerson(Person person) {
		this.personRepository.addPerson(person);
		return person;
	}

	
	@Override
	public List<Person> getPersonsByCity(String city) {
		return personRepository.getPersonsByCity(city);
	}

	public Person getPersonByFirstnameAndLastName(String firstName, String lastName){
		Optional<Person> personTemp = personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
		
		if(personTemp.isPresent()) {
			return personTemp.get();
		}
		else {
			System.out.println("erreur");
			return null;
		}
	}
	
	@Override
	public Person getChildByAddress(String address) {
		Optional<Person> personTemp = personRepository.getChildByAddress(address);
		if(personTemp.isPresent()) {
			return personTemp.get();
		}
		else {
			System.out.println("erreur");
			return null;
		}
	}
	
	@Override
	public List<Person> getPersonByAddress(String address) {
		List<Person> personTemp = personRepository.getPersonByAddress(address);
		return personTemp;
		
		
	}
	
	@Override
	public List<Person> getPhoneByAddress(String address){
		List<Person> personTemp = personRepository.getPhoneByAddress(address);
		return personTemp;
		
	}
	
	

}

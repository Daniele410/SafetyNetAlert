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

import exception.PersonNotFoundException;

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
	public List<Person> getPersonByLastName(String lastName) throws PersonNotFoundException {
		
		List<Person> listPersonByName =  personRepository.findByLastName(lastName);
		if(listPersonByName.isEmpty()) {
			String errorMessage = String.format("%s not found", lastName);
			throw new PersonNotFoundException(errorMessage);
		}
		return listPersonByName;
	}


	@Override
	public Person deletePerson(Person person) {
		// rechercher si la personne existe
		// si elle n'existe pas alors on throw une exception, PersonNotFoundException
		personRepository.deletePerson(person);
		logger.info("Deleting the person with keyname : " + person);
		System.out.println("This person " + "first name = " + person.getFirstName() +" and "+" last name= "+ person.getLastName() + " is deleted");
		return null;
	}

	@Override
	public Person updatePerson(Person person) {
		// Vérifier si la Person existe

		personRepository.updatePerson(person);
		logger.info("Saving the person : " + person);
		return person;
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

	public Person getPersonByFirstnameAndLastName(String firstName, String lastName) {
		Optional<Person> personTemp = personRepository.getPersonByFirstNameAndLastName(firstName, lastName);

		if (personTemp.isPresent()) {
			return personTemp.get();
		} else {
			System.out.println("erreur");
			return null;
		}
	}

	@Override
	public Person getChildByAddress(String address) {
		Optional<Person> personTemp = personRepository.getChildByAddress(address);
		if (personTemp.isPresent()) {
			return personTemp.get();
		} else {
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
	public List<Person> getPhoneByAddress(String address) {
		List<Person> personTemp = personRepository.getPhoneByAddress(address);
		return personTemp;

	}

}

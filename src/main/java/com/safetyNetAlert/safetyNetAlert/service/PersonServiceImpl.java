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
	public List<Person> getPersons() throws PersonNotFoundException  {
		List<Person> persons = new ArrayList<>();
		personRepository.getAllPersons().forEach(persons::add);
		logger.info("Getting all persons");
		if(persons.isEmpty()) {
			String errorMessage = String.format("%s not found", persons);
			throw new PersonNotFoundException(errorMessage);
		}
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
	public Person deletePerson(Person person) throws PersonNotFoundException {
		// rechercher si la personne existe
		// si elle n'existe pas alors on throw une exception, PersonNotFoundException
		person  = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
		if (person != null) {
			personRepository.deletePerson(person);
			return person;
		}
		String errorMessage = String.format("the person is not delete"+" "+ "insert a existing name");
		throw new PersonNotFoundException(errorMessage);
	}

	@Override
	public Person updatePerson(Person person) throws PersonNotFoundException {
		// Vérifier si la Person existe
		Person personToUpdate = personRepository
				.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
		if (personToUpdate != null) {
			return personRepository.updatePerson(person);
		}else
		logger.error("The " + person + " is not present");
		throw new PersonNotFoundException("the "+ person+ " is not update"+" "+ "insert a existing name" );
//		personRepository.updatePerson(person);
//		logger.info("Saving the person : " + person);
//		return person;
	}

	@Override
	public Person addPerson(Person person) {
		
		this.personRepository.addPerson(person);
		
		return person;
	}
//	List<Person> listPersonByName =  personRepository.findByLastName(lastName);
//	if(listPersonByName.isEmpty()) {
//		String errorMessage = String.format("%s not found", lastName);
//		throw new PersonNotFoundException(errorMessage);
//	}
//	return listPersonByName;
	
	@Override
	public List<Person> getPersonsByCity(String city) throws PersonNotFoundException{
		List<Person> listPersonByCity =  personRepository.getPersonsByCity(city);
		if(listPersonByCity.isEmpty()) {
			String errorMessage = String.format("%s not found", city);
			throw new PersonNotFoundException(errorMessage);
		}
		return listPersonByCity;
	}

	public Person getPersonByFirstnameAndLastName(String firstName, String lastName) throws PersonNotFoundException{
		Optional<Person> personTemp = personRepository.getPersonByFirstNameAndLastName(firstName, lastName);

		if (personTemp.isPresent()) {
			return personTemp.get();
		} else {
			logger.error("person {} {} not found", firstName, lastName);
			throw new PersonNotFoundException(
					"person " + firstName + " " + lastName + " not found");
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

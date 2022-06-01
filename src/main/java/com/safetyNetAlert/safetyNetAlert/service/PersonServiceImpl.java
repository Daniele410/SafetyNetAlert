package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		personRepository.getAllPersons().forEach(persons::add);
		return persons;
	}

	@Override
	public Person getPersonByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}

	@Override
	public Person getPersonByLastName(String lastName) {
		return this.getPersonByLastName(lastName);
	}

	@Override
	public Person getPersonByAddress(String address) {
		return this.getPersonByAddress(address);
	}

	@Override
	public Person getPersonByCity(String city) {
		return this.getPersonByAddress(city);
	}

	@Override
	public Person getPersonByZip(String zip) {
		return this.getPersonByZip(zip);
	}

	@Override
	public Person getPersonByPhone(String phone) {
		return this.getPersonByPhone(phone);
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
		personRepository.deletePerson(person);
		return null;
	}

	@Override
	public Person updatePerson(Person person) {
		// Vérifier si la Person existe

		personRepository.updatePerson(person);

		return null;
	}

	@Override
	public Person addPerson(Person person) {
		this.personRepository.addPerson(person);
		return person;
	}

}

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
		// TODO Auto-generated method stub
		return personRepository.findByFirstName(firstName);
	}

	@Override
	public Person getPersonByLastName(String lastName) {
		// TODO Auto-generated method stub
		return this.getPersonByLastName(lastName);
	}

	@Override
	public Person getPersonByAddress(String address) {
		// TODO Auto-generated method stub
		return this.getPersonByAddress(address);
	}

	@Override
	public Person getPersonByCity(String city) {
		// TODO Auto-generated method stub
		return this.getPersonByAddress(city);
	}

	@Override
	public Person getPersonByZip(String zip) {
		// TODO Auto-generated method stub
		return this.getPersonByZip(zip);
	}

	@Override
	public Person getPersonByPhone(String phone) {
		// TODO Auto-generated method stub
		return this.getPersonByPhone(phone);
	}

	@Override
	public Person getPersonByEmail(String email) {
		// TODO Auto-generated method stub
		return this.getPersonByEmail(email);
	}

	public void updatePerson(String personFirstName, Person person) {
		// TODO Auto-generated method stub
	
	}

	public List<Person> deletePerson(String firstName) {
		return null;
		// TODO Auto-generated method stub
		
	}

}

package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.Person;

import exception.PersonNotFoundException;

public interface IPersonService {

	List<Person> getPersons() throws PersonNotFoundException;

	List<Person> getPersonByLastName(String lastName) throws PersonNotFoundException;

	List<Person> getPersonByAddress(String address);

	Person deletePerson(Person person) throws PersonNotFoundException;

	Person updatePerson(Person person) throws PersonNotFoundException;

	Person addPerson(Person person);

	List<Person> getPersonsByCity(String city) throws PersonNotFoundException;

	Person getPersonByFirstnameAndLastName(String firstname, String lastname) throws PersonNotFoundException;

	Person getChildByAddress(String address);

	List<Person> getPhoneByAddress(String address);
	
	

}

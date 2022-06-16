package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.Person;

public interface IPersonService {

	List<Person> getPersons();

	List<Person> getPersonByLastName(String lastName);

	List<Person> getPersonByAddress(String address);

	

	Person getPersonByEmail(String email);

	Person deletePerson(Person person);

	Person updatePerson(Person person);

	Person addPerson(Person person);

	List<Person> getPersonsByCity(String city);

	Person getPersonByFirstnameAndLastName(String firstname, String lastname);

	Person getChildByAddress(String address);

	List<Person> getPhoneByAddress(String address);

}

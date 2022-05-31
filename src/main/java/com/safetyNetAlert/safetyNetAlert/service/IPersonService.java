package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.Person;

public interface IPersonService {

	List<Person> getPersons();
	
	Person getPersonByFirstName(String firstName);
	Person getPersonByLastName(String lastName);
	Person getPersonByAddress(String address);
	Person getPersonByCity(String city);
	Person getPersonByZip(String zip);
	Person getPersonByPhone(String phone);
	Person getPersonByEmail(String email);
	
	
	
}

package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.Person;

@Repository
public class PersonRepository {
	
	private List<Person> listPerson = new ArrayList<>();
	
	public void addPerson(Person person) {
		this.listPerson.add(person);
	}

	public List<Person> getAllPersons() {
		return this.listPerson;
	}

}

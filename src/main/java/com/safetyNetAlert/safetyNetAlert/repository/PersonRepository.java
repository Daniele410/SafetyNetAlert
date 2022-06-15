package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public Person findByFirstName(String firstName) {
		return this.findByFirstName(firstName);
	}

	public void updatePerson(Person person) {
		Person personToUpdate = findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
		int index = listPerson.indexOf(personToUpdate);
		// mise à jour de la personne grâce à l'index dans la liste
		listPerson.set(index, person);
	}

	public void deletePerson(Person person) {
		Person personToDelete = findByFirstNameAndLastName(person.getFirstName(),person.getLastName());
		int index = listPerson.indexOf(personToDelete);
		listPerson.remove(index);
			
			
	}
	
	
	private Person findByFirstNameAndLastName(String firstName, String lastName) {

		Optional<Person> personToFind = listPerson.stream()
				.filter(person -> (person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName)))
				.findFirst();

		/*
		 * for(Person person : listPerson) {
		 * if((person.getFirstName().equals(firstName)) &&
		 * (person.getLastName().equals(lastName))) { return person; } }
		 */

		if (personToFind.isPresent()) {
			return personToFind.get();
		} else
			return null;
	}
	
	public List<Person> getPersonsByCity(String city) {
		
		return this.listPerson
				.stream()
				.filter(person -> person.getCity().equals(city))
				.collect(Collectors.toList());
	}
	
	public Optional<Person> getPersonByFirstNameAndLastName(String firstName, String lastName){
		return this.listPerson
				.stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
				.findFirst();
	}
	
	public Optional<Person> getChildByAddress(String address){
		
		return this.listPerson
				.stream()
				.filter(person -> person.getAddress().equals(address))
				.findFirst();
	}
	
	
}

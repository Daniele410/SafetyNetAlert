package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.Person;

@Repository
public class PersonRepository {

	static final Logger logger = LogManager.getLogger(PersonRepository.class);
	
	private List<Person> listPerson = new ArrayList<>();

	public PersonRepository(List<Person> persons) {
		this.listPerson.addAll(persons);
	}

	public void addPerson(Person person) {
		this.listPerson.add(person);
	}

	public List<Person> getAllPersons() {
		return this.listPerson;
	}

	public List<Person> findByLastName(String lastName) {
		return this.listPerson.stream().filter(p -> p.getLastName().equals(lastName)).collect(Collectors.toList());
	}

	public Person updatePerson(Person person) {
		logger.debug("updating person {}", person);
		Person personUpdate = findByFirstNameAndLastName(person.getFirstName(),
				person.getLastName());
		if (personUpdate != null) {
			deletePerson(personUpdate);
			addPerson(person);
			return person;
		}else
		return null;
//		Person personToUpdate = findByFirstNameAndLastName(person.getFirstName(), person.getLastName());//dans le service, si pas présent on trhow
//		int index = listPerson.indexOf(personToUpdate);
//		return listPerson.set(index, person);
	}

	public void deletePerson(Person person) {
		logger.debug("delete person {}", person);
		this.listPerson.remove(person);
//		
//		Person personToDelete = findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
//		int index = listPerson.indexOf(personToDelete);
//		listPerson.remove(index);

	}

	public Person findByFirstNameAndLastName(String firstName, String lastName) {
		Optional<Person> personToFind = listPerson.stream()
				.filter(person -> (person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName)))
				.findFirst();
		if (personToFind.isPresent()) {
			return personToFind.get();
		} else
			return null;
	}

	public List<Person> getPersonsByCity(String city) {

		return this.listPerson.stream().filter(person -> person.getCity().equals(city)).collect(Collectors.toList());
	}

	public Optional<Person> getPersonByFirstNameAndLastName(String firstName, String lastName) {
		return this.listPerson.stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
				.findFirst();
	}

	public Optional<Person> getChildByAddress(String address) {

		return this.listPerson.stream().filter(person -> person.getAddress().equals(address)).findFirst();
	}

	public List<Person> getPersonByAddress(String address) {
		return this.listPerson.stream().filter(person -> person.getAddress().equals(address)).distinct()
				.collect(Collectors.toList());
	}

	public List<Person> getPhoneByAddress(String address) {
		return this.listPerson.stream().filter(person -> person.getPhone().equals(address))
				.collect(Collectors.toList());
	}

}

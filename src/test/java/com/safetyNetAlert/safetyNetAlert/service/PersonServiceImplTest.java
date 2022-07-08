package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

import exception.PersonNotFoundException;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	PersonServiceImpl personService;

	@Mock
	private PersonRepository personRepository;

	private List<Person> personList = new ArrayList<>();

	// Format test
	// Given
	// When
	// Then
	@Test
	public void getPersonTest_ShouldReturnNotNull() throws Exception {

		// Given
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		personList = Arrays.asList(person);

		// When
		when(personRepository.getAllPersons()).thenReturn(this.personList);

		List<Person> result = personService.getPersons();

		// Then
		assertThat(result).isNotNull();
		assertEquals(result.get(0).getFirstName(), person.getFirstName());
	}

	@Test
	public void getPersonByLastName() throws Exception {

		// Given
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		personList = Arrays.asList(person);

		// When
		when(personRepository.findByLastName(anyString())).thenReturn(personList);
		List<Person> result = personService.getPersonByLastName(anyString());
		// Then
		assertThat(result).isNotNull();
	}

	@Test
	public void getPersonByLastName_ShouldReturnNull() throws Exception {

		// Given // When
		Person person = new Person();
		personList = Arrays.asList(person);

		// Then
		assertThrows(PersonNotFoundException.class, () -> personService.getPersonByLastName(anyString()));

	}

	@Test
	public void updatePersonTest() throws Exception {

		// Given
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		personList = Arrays.asList(person);

		// When
		personRepository.updatePerson(person);

		// Then
		verify(personRepository, Mockito.times(1)).updatePerson(person);

	}

	@Test
	public void updatePersonTest2() throws Exception {

		// Given
		Person person = mock(Person.class);

		// When
		this.personService.updatePerson(person);

		// Then
		verify(personRepository, Mockito.times(1)).updatePerson(person);

	}

	@Test
	public void deletePersonTest() {

		// Given
		Person person = mock(Person.class);

		// When
		this.personService.deletePerson(person);

		// Then
		verify(personRepository, Mockito.times(1)).deletePerson(person);

	}

	@Test
	public void addPersonTest() {

		// Given
		Person person = mock(Person.class);

		// When
		this.personService.addPerson(person);

		// Then
		verify(personRepository, Mockito.times(1)).addPerson(person);

	}

	@Test
	public void getPersonsByCityTest_ShouldReturnNotNull() throws PersonNotFoundException {
		
		// Given // When
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		List<Person> listPersonByCity = List.of(person);

		when(personRepository.getPersonsByCity(anyString())).thenReturn(listPersonByCity);
		List<Person> result = personService.getPersonsByCity(anyString());

		// Then
		verify(personRepository, Mockito.times(1)).getPersonsByCity(anyString());
		assertNotNull(result);
	}

	@Test
	public void getPersonsByCityTest_ShouldReturnNull() throws PersonNotFoundException {

		// Given // When
		List<Person> listPersonByCity = Collections.emptyList();

		// When
		when(personRepository.getPersonsByCity(anyString())).thenReturn(listPersonByCity);

		// Then
		assertThrows(PersonNotFoundException.class, () -> personService.getPersonsByCity(anyString()));
		verify(personRepository, Mockito.times(1)).getPersonsByCity(anyString());

	}

	@Test
	public void getPersonByFirstnameAndLastName_ShouldReturnNull() {

		Optional<Person> optionalPersonEmpty = Optional.empty();
		when(personRepository.getPersonByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(optionalPersonEmpty);

		// Given // When
		Person result = personService.getPersonByFirstnameAndLastName(anyString(), anyString());

		// Then
		assertNull(result);

	}

	@Test
	public void getPersonByFirstnameAndLastName_ShouldReturnPerson() {
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");

		Optional<Person> optionalPerson = Optional.of(person);
		when(personRepository.getPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(optionalPerson);

		// Given // When
		Person result = personService.getPersonByFirstnameAndLastName(anyString(), anyString());

		// Then
		assertNotNull(result);

	}

	@Test
	public void getChildByAddress_ShouldReturnChild() {

		// Given
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");

		Optional<Person> optionalPerson = Optional.of(person);
		// When
		when(personRepository.getChildByAddress(anyString())).thenReturn(optionalPerson);
		Person result = personService.getChildByAddress(anyString());

		// Then
		assertNotNull(result);

		// Then
		verify(personRepository, Mockito.times(1)).getChildByAddress(anyString());

	}

	@Test
	public void getChildByAddress_ShouldReturnNull() {

		Optional<Person> optionalPersonEmpty = Optional.empty();
		when(personRepository.getChildByAddress(anyString())).thenReturn(optionalPersonEmpty);

		// Given // When
		Person result = personService.getChildByAddress(anyString());

		// Then
		assertNull(result);

	}

	@Test
	public void getPersonByAddressTest() {

		// Given // When
		this.personService.getPersonByAddress(anyString());

		// Then
		verify(personRepository, Mockito.times(1)).getPersonByAddress(anyString());

	}

	@Test
	public void getPhoneByAddressTest() {

		// Given // When
		this.personService.getPhoneByAddress(anyString());

		// Then
		verify(personRepository, Mockito.times(1)).getPhoneByAddress(anyString());

	}

}

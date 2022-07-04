package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	PersonServiceImpl personService;

	@Mock
	private PersonRepository personRepository;

	@Mock
	Person person;

	@Mock
	private List<Person> personList = new ArrayList<>();

	// Format test
	// Given
	// When
	// Then
	@Test
	public void getPersonTest() throws Exception {

		// Given
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		personList = Arrays.asList(person);

		// When
		when(personRepository.getAllPersons()).thenReturn(this.personList);

		List<Person> result = personService.getPersons();

		// Then
		assertThat(result).isNotNull();
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
	public void getPersonsByCityTest() {

		// Given // When
		this.personService.getPersonsByCity(anyString());

		// Then
		verify(personRepository, Mockito.times(1)).getPersonsByCity(anyString());

	}
	

	@Test
	public void getPersonByFirstnameAndLastNameTest() {

		// Given // When
		this.personService.getPersonByFirstnameAndLastName(anyString(), anyString());

		// Then
		verify(personRepository, Mockito.times(1)).getPersonByFirstNameAndLastName(anyString(), anyString());

	}

	@Test
	public void getChildByAddress() {

		// Given // When
		this.personService.getChildByAddress(anyString());

		// Then
		verify(personRepository, Mockito.times(1)).getChildByAddress(anyString());

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

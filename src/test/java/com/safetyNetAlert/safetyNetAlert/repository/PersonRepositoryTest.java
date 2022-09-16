package com.safetyNetAlert.safetyNetAlert.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNetAlert.safetyNetAlert.model.Person;

class PersonRepositoryTest {

	private static PersonRepository personRepository;

	private static List<Person> persons = new ArrayList<>();

	static {
		persons.add(
				new Person("John", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		persons.add(new Person("Jacob", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513", "drk@email.com"));
		persons.add(
				new Person("Tessa", "Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com"));
		persons.add(
				new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878", "soph@email.com"));
	}

	@BeforeEach
	void setUp() throws Exception {
		personRepository = new PersonRepository(persons);
	}

	@Test
	void getAllPersonsTest_ShouldReturnPersonList() {
		// Given

		List<Person> result = personRepository.getAllPersons();
		// Then
		assertThat(result).isEqualTo(persons);
	}

	@Test
	void addPersonTest_ShouldReturnPensonList() {
		// Given
		Person person = new Person("Gimmy", "Boy", "1509 Culver st", "Culver", "97451", "841-874-6544",
				"rogerboyd@email.com");
		// When
		personRepository.addPerson(person);
		persons.add(person);
		// Then
		assertThat(persons).contains(person);
	}

	@Test
	void updatePersonTest_ShouldReturnNull() {

		// Given
		Person personNull = new Person(null, null, "1986 Culver st", "Culver", "97451", "841-874-2222",
				"jboyd@email.com");

		// When
		Person result = personRepository.updatePerson(personNull);

		// Then
		assertNull(result);
	}

	@Test
	void deletePersonTest_shouldRemovePerson() {
		// Given
		Person person = new Person("John", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		// When
		personRepository.deletePerson(person);
		// Then
		assertThat(persons).doesNotContain(person);
	}

	@Test
	void findPersonByNameTest_shouldReturnPersonGivenName() {
		// Given
		Person person = new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878",
				"soph@email.com");
		// When
		persons.add(person);
		Person result = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());

		// Then
		assertThat(persons).contains(person);
		assertNotNull(result);

	}

	@Test
	void findPersonByNameTest_shouldReturnNull() {

		// Given
		Person person = new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878",
				"soph@email.com");

		// When
		Person result = personRepository.findByFirstNameAndLastName(person.getLastName(), person.getLastName());

		// Then
		assertNull(result);

	}

	@Test
	void findPersonByName_test_withAnUnregisteredPerson_shouldReturnNull() {

		// Given // When
		Person result = personRepository.findByFirstNameAndLastName("lola", "laod");

		// Then
		assertThat(result).isNull();

	}

	@Test
	void findPersonsByLastNameTest_ShouldReturnNotNull() {

		// Given
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513",
				"drk@email.com");

		// When
		List<Person> result = personRepository.findByLastName(person.getLastName());

		// Then

		assertNotNull(result);
	}

	@Test
	void findPersonsByLastName_withNon_ExistentName_returnEmptyList() {

		// Given
		List<Person> result = personRepository.findByLastName("Pippo");

		// When
		assertThat(result).isEmpty();
	}

	@Test
	void findByCityTest_shouldReturnListOfPerson() {

		// Given
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513",
				"drk@email.com");

		// When
		persons.add(person);
		List<Person> result = personRepository.getPersonsByCity("Culver st");

		// Then
		assertThat(persons).contains(person);
		assertNotNull(result);
	}

	@Test
	void findByCityTest_withAnUnregisteredCity_returnEmptyList() {

		// Given //When
		List<Person> result = personRepository.getPersonsByCity("Rome");

		// Then
		assertThat(result).isEmpty();
	}

	@Test
	void updatePersonTest_ShouldReturnNotNull() {

		// Given
		Person person = new Person("John", "Boyd", "1986 Culver st", "Culver", "97451", "841-874-2222",
				"jboyd@email.com");

		// When
		Person result = personRepository.updatePerson(person);
		persons.add(person);

		// Then
		assertNotNull(result);
	}

	@Test
	void findByAddressTest_shouldReturnListPersonLivingInTheAddress() {

		// Then
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513",
				"drk@email.com");

		// When
		persons.add(person);
		List<Person> result = personRepository.getPersonByAddress(person.getAddress());

		// Then
		assertThat(persons).contains(person);
		assertNotNull(result);

	}

	@Test
	void findByAddress_withAnUnregisteredAddress_retunNull() {

		// Given //When
		List<Person> result = personRepository.getPhoneByAddress("152 francisco st");

		// Then
		assertThat(result).isEmpty();

	}

	@Test
	public void getPersonByFirstNameAndLastNameTest_shouldReturnPerson() {

		// Given // When
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513",
				"drk@email.com");
		persons.add(person);
		personRepository.getPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());

		// Then
		assertThat(persons).contains(person);

	}

	@Test
	public void getChildByAddressTest_shouldReturnPerson() {

		// Given // When
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513",
				"drk@email.com");
		persons.add(person);
		personRepository.getChildByAddress(person.getAddress());

		// Then
		assertThat(persons).contains(person);

	}

}

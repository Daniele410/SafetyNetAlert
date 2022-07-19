package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.FloodDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonAtAddressDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonByFirestationDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

import exception.FirestationNotFoundException;
import exception.MedicalRecordNotFoundException;
import exception.PersonNotFoundException;

@ExtendWith(MockitoExtension.class)
class AlertServiceImplTest {

	@InjectMocks
	private AlertServiceImpl alertService;

	@Mock
	private static IPersonService personService;

	@Mock
	private static IFirestationService firestationService;

	@Mock
	private static IMedicalRecordService medicalRecordService;

	@Mock
	private AgeCalculator ageCalculator;

//	private static LogCaptor logcaptor;

	private static List<Person> persons = new ArrayList<>();
	static {
		persons.add(
				new Person("Jonny", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		persons.add(new Person("Gimmy", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513", "drk@email.com"));
		persons.add(new Person("Mike", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512", "reg@email.com"));
		persons.add(
				new Person("Tata", "Popo", "892 Downing Ct", "Culver", "97451", "841-874-7878", "gramps@email.com"));
	}

	private static List<Firestation> firestations = new ArrayList<>();
	static {
		firestations.add(new Firestation("112 Steppes Pl", "4"));
		firestations.add(new Firestation("947 E. Rose Dr", "2"));
		firestations.add(new Firestation("1509 Culver st", "1"));

	}

	private static List<MedicalRecord> medicalRecords = new ArrayList<>();
	static {

		medicalRecords.add(new MedicalRecord("Jonny", "Boyd", "03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("illisoxian"))));
		medicalRecords.add(new MedicalRecord("Gimmy", "Boyd", "03/06/1989",
				new ArrayList<String>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")),
				new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Mike", "Boyd", "03/03/2015",
				new ArrayList<String>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")),
				new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Tata", "Popo", "03/04/2016",
				new ArrayList<String>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")),
				new ArrayList<String>()));
	}

	// Format test
	// Given
	// When
	// Then

	@BeforeEach
	public void setup() {
//		logcaptor = LogCaptor.forName("AlertServiceImpl");
//		logcaptor.setLogLevelToInfo();
	}

	@Test
	void getCommunityEmailTest() throws Exception {

		// Given
		when(personService.getPersonsByCity(anyString())).thenReturn(persons);

		// When
		List<String> result = alertService.getCommunityEmail("Culver");

		// Then
		assertThat(result).containsAll(new ArrayList<String>(
				List.of("jaboyd@email.com", "gramps@email.com", "reg@email.com", "drk@email.com")));
		verify(personService).getPersonsByCity(anyString());
	}

	@Test
	void getPersonInfoTest() throws PersonNotFoundException, MedicalRecordNotFoundException {
		// Given
		when(personService.getPersonByLastName(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Gimmy", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		ageCalculator.calculate(medicalRecords.get(0).getBirthdate());
		ageCalculator.calculate(medicalRecords.get(1).getBirthdate());

		// When
		List<PersonInfoDto> result = alertService.getPersonInfo(anyString());

		// Then
		assertThat(result.size()).isEqualTo(2);
		verify(medicalRecordService, times(2)).getMedicalRecordByFirstNameAndLastName(anyString(), anyString());
		verify(personService).getPersonByLastName(anyString());

	}

	@Test
	void getPersonsPhoneNumberByStationTest() throws Exception {

		// Given
		when(firestationService.getFireStationsByStation(anyString()))
				.thenReturn(new ArrayList<Firestation>(List.of(firestations.get(0))));
		when(personService.getPersonByAddress(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));

		// When
		Set<String> result = alertService.getPersonsPhoneNumberByStation(anyString());

		// Then
		assertThat(result).contains("841-874-6512");
		assertThat(result).contains("841-874-6513");
		verify(personService).getPersonByAddress(anyString());
		verify(firestationService).getFireStationsByStation(any());

	}

	@Test
	void getPersonsPhoneNumberByStationTest_ShouldReturnException() throws PersonNotFoundException {

		// Given // When // Then
		assertThrows(PersonNotFoundException.class, () -> alertService.getPersonsPhoneNumberByStation(any()));
		containsString("The list is empty, try again");

	}

	@Test
	void getChildrenByAddress_Test() throws PersonNotFoundException, MedicalRecordNotFoundException {

		// Given
		when(personService.getPersonByAddress(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1), persons.get(2))));
		when(medicalRecordService.isChild(persons.get(0).getFirstName(), persons.get(0).getLastName()))
				.thenReturn(false);
		when(medicalRecordService.isChild(persons.get(1).getFirstName(), persons.get(1).getLastName()))
				.thenReturn(false);
		when(medicalRecordService.isChild(persons.get(2).getFirstName(), persons.get(0).getLastName()))
				.thenReturn(true);
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Mike", "Boyd"))
				.thenReturn(medicalRecords.get(2));
		ageCalculator.calculate(medicalRecordService.getMedicalRecordByFirstNameAndLastName(
				medicalRecords.get(0).getFirstName(), medicalRecords.get(0).getLastName()).getBirthdate());
		ageCalculator.calculate(medicalRecordService.getMedicalRecordByFirstNameAndLastName(
				medicalRecords.get(0).getFirstName(), medicalRecords.get(1).getLastName()).getBirthdate());
		ageCalculator.calculate(medicalRecordService.getMedicalRecordByFirstNameAndLastName(
				medicalRecords.get(0).getFirstName(), medicalRecords.get(2).getLastName()).getBirthdate());

		// When
		List<ChildDto> result = alertService.getChildDto("1509 Culver St");

		// Then
		verify(personService).getPersonByAddress(anyString());
		verify(medicalRecordService, times(3)).getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd");
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	void getPersonsByAddressFromListOfStationNumberTest() throws PersonNotFoundException, FirestationNotFoundException, MedicalRecordNotFoundException {

		// Given

		when(personService.getPersonByAddress(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1), persons.get(2))));
		when(firestationService.getFirestationsByAddress(anyString())).thenReturn(Optional.of(firestations.get(2)));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Gimmy", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Mike", "Boyd"))
				.thenReturn(medicalRecords.get(2));
		ageCalculator.calculate(medicalRecordService.getMedicalRecordByFirstNameAndLastName(
				medicalRecords.get(0).getFirstName(), medicalRecords.get(0).getLastName()).getBirthdate());
		ageCalculator.calculate(medicalRecordService.getMedicalRecordByFirstNameAndLastName(
				medicalRecords.get(0).getFirstName(), medicalRecords.get(1).getLastName()).getBirthdate());
		ageCalculator.calculate(medicalRecordService.getMedicalRecordByFirstNameAndLastName(
				medicalRecords.get(0).getFirstName(), medicalRecords.get(2).getLastName()).getBirthdate());

		// When

		List<PersonAtAddressDto> result = alertService.getPersonsByAddressFromListOfStationNumber("1509 Culver st");

		// Then
		verify(personService).getPersonByAddress(anyString());
		verify(medicalRecordService, times(4)).getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd");
		assertThat(result.size()).isEqualTo(3);

	}

	@Test
	void getPersonsByAddressFromListOfStationNumberTest_shouldReturnException()
			throws PersonNotFoundException, FirestationNotFoundException {

		// Given // When // Then
		assertThrows(FirestationNotFoundException.class,
				() -> alertService.getPersonsByAddressFromListOfStationNumber("1509 Culver st"));
		containsString("Firestation not found");

	}

	@Test
	void getPersonsBySameAddressTest() throws PersonNotFoundException, FirestationNotFoundException {
		List<String> fireList = List.of("112 Steppes Pl", "1509 Culver st", "947 E. Rose Dr");
		
		
		when(personService.getPersons()).thenReturn(persons);
		when(firestationService.getAddressesCoveredByStationNumber(anyString())).thenReturn(fireList);
		
	
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(),anyString()))
				.thenReturn(medicalRecords.get(2));
		
		alertService.ageCalculator = new AgeCalculator();
		
	
		List<FloodDto> result = alertService.getPersonsBySameAddress("1509 Culver st");
		
		verify(personService).getPersons();
		verify(medicalRecordService, times(1)).getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd");
		assertThat(result.size()).isEqualTo(3);
		
	}
	
	@Test
	void getPersonsCoveredByStationTest() throws PersonNotFoundException {
		
		// Given
		List<String> fireList = List.of("112 Steppes Pl", "1509 Culver st", "947 E. Rose Dr");
		
		when(personService.getPersons()).thenReturn(persons);

		when(firestationService.getAddressesCoveredByStationNumber(anyString())).thenReturn(fireList);

		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(),anyString()))
		.thenReturn(medicalRecords.get(2));


		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Gimmy", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Mike", "Boyd"))
				.thenReturn(medicalRecords.get(2));
		when(ageCalculator.calculate(medicalRecords.get(0).getBirthdate())).thenReturn(38);
		when(ageCalculator.calculate(medicalRecords.get(1).getBirthdate())).thenReturn(33);
		when(ageCalculator.calculate(medicalRecords.get(2).getBirthdate())).thenReturn(7);
		


		// When
		PersonByFirestationDto result = alertService.getPersonsCoveredByStation("1");

		// Then
		verify(personService).getPersons();
		verify(firestationService).getAddressesCoveredByStationNumber("1");

		assertThat(result.getNbOfChildren()).isEqualTo(1);
		assertThat(result.getNbOfAdult()).isEqualTo(2);
	}

	

//	@Test
//	public void testerror() {
////		assertThat(logcaptor.getErrorLogs()).contains("Error parsing user input for type of vehicle");
//	}

}

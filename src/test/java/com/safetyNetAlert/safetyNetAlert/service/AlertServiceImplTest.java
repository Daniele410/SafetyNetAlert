package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonAtAddressDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

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

	private static List<Person> persons = new ArrayList<>();
	static {
		persons.add(
				new Person("Jonny", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		persons.add(new Person("Gimmy", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513", "drk@email.com"));
		persons.add(new Person("Toto", "Tutu", "892 Downing Ct", "Culver", "97451", "841-874-6512", "reg@email.com"));
		persons.add(
				new Person("Tata", "Popo", "892 Downing Ct", "Culver", "97451", "841-874-7878", "gramps@email.com"));
	}

	private static List<Firestation> firestations = new ArrayList<>();
	static {
		firestations.add(new Firestation("112 Steppes Pl", "4"));
		firestations.add(new Firestation("947 E. Rose Dr", "1"));
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
	}

	// Format test
	// Given
	// When
	// Then

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
	void getPersonInfoTest() throws Exception {
		// Given
		when(personService.getPersonByLastName(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Gimmy", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		when(ageCalculator.calculate(anyString())).thenReturn(38);
		when(ageCalculator.calculate(anyString())).thenReturn(33);

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
	void getChildDtoTest() throws Exception {
		// Given
		
		when(personService.getPersonByAddress(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));

//		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Jonny", "Boyd"))
//				.thenReturn(medicalRecords.get(0));
//		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Gimmy", "Boyd"))
//				.thenReturn(medicalRecords.get(1));
//		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(), anyString())).thenReturn(any());
//		when(ageCalculator.calculate("03/06/1984")).thenReturn(38);
//		when(ageCalculator.calculate("03/06/1989")).thenReturn(33);

		// When
		List<ChildDto> result = alertService.getChildDto("1509 Culver st");

		// Then

		assertThat(result).isEmpty();
		verify(personService).getPersonByAddress(anyString());
//		verify(medicalRecordService, times(1)).getMedicalRecordByFirstNameAndLastName(anyString(),anyString());

	}
	@Disabled
	@Test
	void getPersonsByAddressFromListOfStationNumberTest() throws Exception {

		// Given
		when(personService.getPersonByAddress(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));

		when(firestationService.getFirestationsByAddress(anyString()))
				.thenReturn(firestationService.getFirestationsByAddress(anyString()));
		
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(), anyString()));
		when(ageCalculator.calculate("03/06/1984")).thenReturn(38);
		when(ageCalculator.calculate("03/06/1989")).thenReturn(33);
	
	// When
	List<PersonAtAddressDto> result = alertService.getPersonsByAddressFromListOfStationNumber(anyString());

	// Then
	assertThat(result.get(0).getStation()).isEqualTo(any());
	}
}

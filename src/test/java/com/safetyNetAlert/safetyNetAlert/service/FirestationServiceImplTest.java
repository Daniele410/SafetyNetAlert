package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.FirestationRepository;

@ExtendWith(MockitoExtension.class)
class FirestationServiceImplTest {

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	FirestationServiceImpl firestationService;

	@Mock
	private FirestationRepository firestationRepository;

	@Mock
	Person person;

	@Mock
	private List<Firestation> firestationList = new ArrayList<>();

	// Format test
	// Given
	// When
	// Then
	@Test
	public void getFirestationTest() throws Exception {

		// Given
		Firestation firestations = new Firestation("1509 Culver St", "1");
		firestationList = Arrays.asList(firestations);

		// When
		when(firestationRepository.getAllFirestation()).thenReturn(this.firestationList);

		List<Firestation> result = firestationService.getFirestations();

		// Then
		assertThat(result).isNotNull();
	}
	
	@Test
	public void updateFirestationTest() throws Exception {

		// Given
		Firestation firestations = new Firestation("1509 Culver St", "1");
		firestationList = Arrays.asList(firestations);

		// When
		firestationService.updateFirestation(firestations);

		// Then
		verify(firestationRepository, Mockito.times(1)).updateFirestation(firestations);

	}
	
	@Test
	public void addFirestationTest() throws Exception {

		// Given
		Firestation firestations = new Firestation("1509 Culver St", "1");
		firestationList = Arrays.asList(firestations);

		// When
		firestationService.addFirestation(firestations);

		// Then
		verify(firestationRepository, Mockito.times(1)).addFirestation(firestations);

	}
	
	@Test
	public void deleteFirestationTest() throws Exception {

		// Given
		Firestation firestations = new Firestation("1509 Culver St", "1");
		firestationList = Arrays.asList(firestations);

		// When
		firestationService.deleteFirestation(firestations);

		// Then
		verify(firestationRepository, Mockito.times(1)).deleteFirestation(firestations);

	}
	
	@Test
	public void getFirestationsByAddressTest() throws Exception {

		// Given
		this.firestationService.getFirestationsByAddress(anyString());

		// Then
		verify(firestationRepository, Mockito.times(1)).getFirestationsByAddress(anyString());

	}
	
	@Test
	public void getAddressesCoveredByStationNumberTest() throws Exception {

		// Given
		this.firestationService.getAddressesCoveredByStationNumber(anyString());

		// Then
		verify(firestationRepository, Mockito.times(1)).getAddressesCoveredByStationNumber(anyString());

	}
	
	@Test
	public void getFireStationsByStationTest() throws Exception {

		// Given
		this.firestationService.getFireStationsByStation(anyString());

		// Then
		verify(firestationRepository, Mockito.times(1)).getFireStationsByStation(anyString());

	}

}

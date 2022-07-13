package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import exception.FirestationNotFoundException;

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
	public void getFirestationTest_shouldReturnException() throws Exception {

		Firestation firestation = new Firestation();
		firestationList = Arrays.asList(firestation);

		// Then
		assertThrows(FirestationNotFoundException.class, () -> firestationService.getFirestations());
		containsString("%s not found");
		
	}
	
	@Test
	public void updateFirestationTest() throws FirestationNotFoundException {

		// Given
				Firestation firestation = new Firestation("1509 Culver St", "1");
				firestationList = Arrays.asList(firestation);
				
				// When
				when(firestationRepository.findByAddress(anyString())).thenReturn(firestationList.get(0));
				firestationService.updateFirestation(firestation);

				// Then
				verify(firestationRepository, Mockito.times(1)).updateFirestation(firestation);
	}
	
	@Test
	public void updateFirestationTest_shouldReturnException() throws FirestationNotFoundException {

		// Given // When
				Firestation firestation = new Firestation();
				firestationList = Arrays.asList(firestation);

				// Then
				assertThrows(FirestationNotFoundException.class, () -> firestationService.updateFirestation(firestation));
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

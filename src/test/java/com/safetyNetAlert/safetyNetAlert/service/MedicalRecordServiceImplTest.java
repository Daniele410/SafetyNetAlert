package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

import exception.MedicalRecordNotFoundException;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceImplTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AgeCalculator ageCalculator;

	@InjectMocks
	MedicalRecordServiceImpl medicalRecordService;

	@Mock
	private MedicalRecordRepository medicalRecordRepository;

	
	MedicalRecord medicalRecord;

	
	private List<MedicalRecord> medicalRecordsList = new ArrayList<>();

	// Format test
	// Given
	// When
	// Then
	@Test
	public void getMedicalRecordsTest() throws Exception {

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		medicalRecordsList = Arrays.asList(medicalRecords);

		// When
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(this.medicalRecordsList);
		List<MedicalRecord> result = medicalRecordService.getMedicalRecords();

		// Then
		assertThat(result).isNotNull();
	}

	@Test
	public void getMedicalRecordsTest_shouldReturnException() throws MedicalRecordNotFoundException {

		// Given // When
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecordsList = Arrays.asList(medicalRecord);

		// Then
		assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.getMedicalRecords());
	}

	@Test
	public void updateMedicalRecordTest() throws MedicalRecordNotFoundException {

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		Optional<MedicalRecord> optionalMedicalRecord = Optional.of(medicalRecords);

		// When
		when(medicalRecordRepository.findByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(optionalMedicalRecord);
		medicalRecordService.updateMedicalRecord(medicalRecords);

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).updateMedicalRecord(medicalRecords);
	}

	@Test
	public void updateMedicalRecordTest_shouldReturnException() throws MedicalRecordNotFoundException {

		// Given // When
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecordsList = Arrays.asList(medicalRecord);

		// Then
		assertThrows(MedicalRecordNotFoundException.class,
				() -> medicalRecordService.updateMedicalRecord(medicalRecord));
	}


	@Test
	public void addMedicalRecordTest() throws Exception {

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		medicalRecordsList = Arrays.asList(medicalRecords);

		// When
		medicalRecordService.addMedicalRecord(medicalRecords);

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).addMedicalRecord(medicalRecords);
	}

	@Test
	public void deleteMedicalRecordTest() throws Exception {

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		medicalRecordsList = Arrays.asList(medicalRecords);

		// When
		medicalRecordService.deleteMedicalRecord(medicalRecords);

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).deleteMedicalRecord(medicalRecords);
	}

	@Test
	public void getMedicalRecordByFirstNameAndLastNameTest() throws Exception {

		String firstName = "Jimmy";
		String lastName = "Sax";
		// Given
		Optional<MedicalRecord> medicalRecordTemp = medicalRecordRepository.findByFirstNameAndLastName(lastName,
				firstName);

		// When
		when(medicalRecordRepository.findByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(medicalRecordTemp);
		medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(), anyString());

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName(lastName, firstName);
	}

	@Test
	public void getMedicalRecordByFirstNameAndLastNameTest_ShoulMediacalRecordFirstName() throws Exception {

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		medicalRecordsList = Arrays.asList(medicalRecords);

		// When
		medicalRecordService.getMedicalRecordByFirstNameAndLastName(medicalRecords.getFirstName(),
				medicalRecords.getLastName());

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName("Jimmy", "Sax");
		assertEquals(medicalRecords.getFirstName(), "Jimmy");

	}
	
	

	
	@Test
	public void isChildTestShouldReturnFalse() {
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1951", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		Optional<MedicalRecord> medicalBox = Optional.of(medicalRecords);

		when(medicalRecordRepository.findByFirstNameAndLastName(anyString(),
				anyString())).thenReturn(medicalBox);
		medicalRecordService.ageCalculator = new AgeCalculator();
		//When
		boolean result = medicalRecordService.isChild("Jimmy", "Sax");
		
		//THEN
		assertFalse(result);
	}
	
	@Test
	public void isChildTestShouldReturnTrue() {
		MedicalRecord medicalRecords = new MedicalRecord("Bob", "DylanKid", "03/06/2021", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		Optional<MedicalRecord> medicalBox = Optional.of(medicalRecords);

		when(medicalRecordRepository.findByFirstNameAndLastName(anyString(),
				anyString())).thenReturn(medicalBox);
		medicalRecordService.ageCalculator = new AgeCalculator();
		//When
		boolean result = medicalRecordService.isChild("Bob", "DylanKid");
		
		//THEN
		assertTrue(result);
	}
//		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1951", Arrays.asList("aznol:350mg"),
//				Arrays.asList("nillacilan"));
//		Optional<MedicalRecord> optionalMedicalRecord = Optional.of(medicalRecords);
//
//		when(medicalRecordRepository.findByFirstNameAndLastName(medicalRecords.getLastName(),
//				medicalRecords.getFirstName())).thenReturn(optionalMedicalRecord);
//
//		AgeCalculator ageCalculator = new AgeCalculator();
//		int age = ageCalculator.calculate(medicalRecordsList.get(0).getBirthdate());
//		medicalRecordService.isChild(medicalRecords.getLastName(), medicalRecords.getFirstName());
//
////		verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName("Jimmy", "Sax");
//		
//		assertEquals(age, 71);
//
//	}

}

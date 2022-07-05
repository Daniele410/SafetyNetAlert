package com.safetyNetAlert.safetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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

	@Mock
	MedicalRecord medicalRecord;

	@Mock
	private List<MedicalRecord> medicalRecordsList = new ArrayList<>();

	// Format test
	// Given
	// When
	// Then
	@Test
	public void getPersonTest() throws Exception {

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
	public void updateMedicalRecordTest() throws Exception {

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		medicalRecordsList = Arrays.asList(medicalRecords);

		// When
		medicalRecordService.updateMedicalRecord(medicalRecords);

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).updateMedicalRecord(medicalRecords);
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

		// Given
		MedicalRecord medicalRecords = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));
		medicalRecordsList = Arrays.asList(medicalRecords);

		// When
		medicalRecordService.getMedicalRecordByFirstNameAndLastName(medicalRecords.getFirstName(),
				medicalRecords.getLastName());

		// Then
		verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName("Jimmy", "Sax");
	}
	@Disabled
	@Test
	public void isChildTest() throws Exception {

		// Given
		this.medicalRecordService.isChild(anyString(), anyString());
		when(ageCalculator.calculate(anyString())).thenReturn(any());
		
		verify(medicalRecordRepository, Mockito.times(1)).findByBirthdate(anyString());
	}

}

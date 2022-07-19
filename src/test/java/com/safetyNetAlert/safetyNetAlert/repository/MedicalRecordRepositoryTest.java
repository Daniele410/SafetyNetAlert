package com.safetyNetAlert.safetyNetAlert.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;

class MedicalRecordRepositoryTest {

	private static MedicalRecordRepository medicalRecordRepository;

	private static List<MedicalRecord> medicalRecords = new ArrayList<>();
	static {

		medicalRecords.add(new MedicalRecord("Clive", "Ferguson", "03/06/1994",
				new ArrayList<String>(List.of("ibupurin:200mg", "hydrapermazol:400mg")),
				new ArrayList<String>(List.of("nillacilan"))));
		medicalRecords.add(new MedicalRecord("John", "Boyd", "03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan"))));
	}

	@BeforeEach
	void setUp() throws Exception {
		medicalRecordRepository = new MedicalRecordRepository(medicalRecords);
	}

	@Test
	void findAllTest_ShouldLIstMedicalRecord() {

		// Given //When
		List<MedicalRecord> result = medicalRecordRepository.getAllMedicalRecords();

		// Then
		assertThat(result).isEqualTo(medicalRecords);
	}

	@Test
	void deleteMedicalRecordTest_ShouldReturnNameToList() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));

		// When
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);

		// Then
		assertThat(medicalRecords).doesNotContain(medicalRecord);

	}

	@Test

	void findByNameTest_shouldReturnNotNull() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));

		// When
		Optional<MedicalRecord> result = medicalRecordRepository
				.findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());

		// Then
		assertNotNull(result);
	}

	@Test
	void addMedicalRecordTest_shouldReturnNotNull() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord("Sarah", "Boyd", "03/06/1988",
				new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));

		// When
		medicalRecordRepository.addMedicalRecord(medicalRecord);

		// Then
		assertNotNull(medicalRecord);

	}

	@Test
	void updateMedicalRecordTest_ShouldReturnMedicalRecordUpload() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord("Clive", "Ferguson", "03/06/1994", new ArrayList<String>(),
				new ArrayList<String>(List.of("nillacilan")));

		// When
		MedicalRecord result = medicalRecordRepository.updateMedicalRecord(medicalRecord);

		// Then
		assertThat(result.getAllergies()).isEqualTo(medicalRecord.getAllergies());

	}
	
	
	
	

	

}

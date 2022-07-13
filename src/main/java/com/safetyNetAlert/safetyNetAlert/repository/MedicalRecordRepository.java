package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

@Repository
public class MedicalRecordRepository {

	static final Logger logger = LogManager.getLogger(MedicalRecordRepository.class);
	
	@Autowired
	AgeCalculator ageCalculator;

	private List<MedicalRecord> listMedicalRecord = new ArrayList<>();

	public MedicalRecordRepository(List<MedicalRecord> medicalRecords) {
		this.listMedicalRecord.addAll(medicalRecords);
	}

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		this.listMedicalRecord.add(medicalRecord);
	}

	public List<MedicalRecord> getAllMedicalRecords() {
		return this.listMedicalRecord;
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		logger.debug("updating firestation {}", medicalRecord);
		Optional<MedicalRecord> medicalRecordsToUpdate = findByFirstNameAndLastName(
				medicalRecord.getFirstName(),
				medicalRecord.getLastName());
		if (medicalRecordsToUpdate != null) {
			deleteMedicalRecord(medicalRecordsToUpdate.get());
			addMedicalRecord(medicalRecord);
			return medicalRecord;
		}
		return null;
		

//		MedicalRecord medicalRecordToUpdate = findByFirstNameAndLastName(medicalRecord.getFirstName(),
//				medicalRecord.getLastName()).get();
//
//		int index = listMedicalRecord.indexOf(medicalRecordToUpdate);
//		return listMedicalRecord.set(index, medicalRecord);
	}

	public MedicalRecord findByBirthdate(String birthdate) {
		return this.findByBirthdate(birthdate);
	}

	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordToDelete = findByFirstNameAndLastName(medicalRecord.getFirstName(),
				medicalRecord.getLastName()).get();
		int index = listMedicalRecord.indexOf(medicalRecordToDelete);
		listMedicalRecord.remove(index);
	}

	public Optional<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName) {
		return this.listMedicalRecord.stream().filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName)
				&& medicalRecord.getLastName().equals(lastName)).findFirst();
	}

}

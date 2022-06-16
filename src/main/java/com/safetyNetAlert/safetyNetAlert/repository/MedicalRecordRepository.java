package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

@Repository
public class MedicalRecordRepository {

	@Autowired
	AgeCalculator ageCalculator;

	private List<MedicalRecord> listMedicalRecord = new ArrayList<>();

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		this.listMedicalRecord.add(medicalRecord);
	}

	public List<MedicalRecord> getAllMedicalRecords() {
		return this.listMedicalRecord;
	}

	public void updateMedicalRecord(MedicalRecord medicalRecord) {

		MedicalRecord medicalRecordToUpdate = findByFirstNameAndLastName(medicalRecord.getFirstName(),
				medicalRecord.getLastName()).get();

		int index = listMedicalRecord.indexOf(medicalRecordToUpdate);
		// mise à jour de la personne grâce à l'index dans la liste
		listMedicalRecord.set(index, medicalRecord);
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

	public Optional<MedicalRecord> findByFirstNameAndLastName(String lastName, String firstName) {
		return this.listMedicalRecord.stream().filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName)
				&& medicalRecord.getLastName().equals(lastName)).findFirst();
	}

}

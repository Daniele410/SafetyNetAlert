package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@Autowired
	AgeCalculator ageCalculator;

	public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecordRepository.getAllMedicalRecords().forEach(medicalRecords::add);
		return medicalRecords;
	}

	/**
	 * Check if there is already
	 */
	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.updateMedicalRecord(medicalRecord);
		return null;
	}

	/**
	 * // Add New Medical Record
	 */
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecordRepository.addMedicalRecord(medicalRecord);
		return medicalRecord;
	}

	/*
	 * Delete it if it is present
	 */
	@Override
	public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);
		return null;
	}

	@Override
	public MedicalRecord getMedicalRecordByFirstNameAndLastName(String lastName, String firstName) {
		Optional<MedicalRecord> medicalRecordTemp = medicalRecordRepository.findByFirstNameAndLastName(lastName,
				firstName);

		if (medicalRecordTemp.isPresent()) {
			return medicalRecordTemp.get();
		} else {
			System.out.println("erreur");
			return null;
		}
	}

	@Override
	public boolean isChild(String lastName, String firstName) {
		MedicalRecord medicalRecordToFind = medicalRecordRepository.findByFirstNameAndLastName(lastName, firstName)
				.get();
		int maxAge = 18;
		if (ageCalculator.calculate(medicalRecordToFind.getBirthdate()) <= maxAge) {
			return true;
		}

		else

			return false;
	}

}

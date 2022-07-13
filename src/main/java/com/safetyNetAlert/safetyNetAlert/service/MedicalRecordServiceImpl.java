package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

import exception.MedicalRecordNotFoundException;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	static final Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@Autowired
	AgeCalculator ageCalculator;

	public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}

	@Override
	public List<MedicalRecord> getMedicalRecords() throws MedicalRecordNotFoundException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecordRepository.getAllMedicalRecords().forEach(medicalRecords::add);
		logger.info("Getting all medicalRecords");
		if(medicalRecords.isEmpty()) {
			String errorMessage = String.format("%s not found", medicalRecords);
			throw new MedicalRecordNotFoundException(errorMessage);
		}
		return medicalRecords;
	}

	/**
	 * Check if there is already
	 */
	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord)  {
		medicalRecordRepository.updateMedicalRecord(medicalRecord);
		return medicalRecord;
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
		logger.info("Deleting the person with keyname : " + medicalRecord);
		System.out.println("This person with medical record " + " first name = " + medicalRecord.getFirstName() +" and "+" last name= "+ medicalRecord.getLastName() + " is deleted");
		return medicalRecord;
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

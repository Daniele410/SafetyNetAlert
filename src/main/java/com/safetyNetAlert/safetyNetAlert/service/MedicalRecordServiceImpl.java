package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

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

}

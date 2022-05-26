package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@GetMapping(value = "/medicalRecords/getAll")
	public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
		return new ResponseEntity<>(medicalRecordRepository.getAllMedicalRecords(), HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
//		medicalRecordRepository.addMedicalRecord(medicalRecord);
//		return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
//	}
	
	
}

package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.service.IMedicalRecordService;



@RestController
public class MedicalRecordController {
	
	private static final Logger logger = LogManager.getLogger("MedicalRecordController");

	@Autowired
	private IMedicalRecordService medicalRecordService;

	@GetMapping(value = "/medicalRecord")
	public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
		List<MedicalRecord> medicalRecordsList = medicalRecordService.getMedicalRecords();
		logger.info("GET request for medicalRecord send");
		logger.info("Response for the GET request for medicalRecord: " + medicalRecordsList);
		return new ResponseEntity<>(medicalRecordService.getMedicalRecords(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		medicalRecordService.addMedicalRecord(medicalRecord);
		return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
	}
	
	 @PutMapping(value = "/medicalRecord")
	    public ResponseEntity<MedicalRecord> updateMedicalRecord( @RequestBody MedicalRecord medicalRecord) {
	        return new ResponseEntity<>(medicalRecordService.updateMedicalRecord(medicalRecord), HttpStatus.OK);
	    }
	
	 @DeleteMapping(value = "/medicalRecord")
		public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
			return new ResponseEntity<>(medicalRecordService.deleteMedicalRecord(medicalRecord), HttpStatus.OK);
		}
	
}

package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

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

	@Autowired
	private IMedicalRecordService medicalRecordService;

	@GetMapping(value = "/medicalRecord")
	public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
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

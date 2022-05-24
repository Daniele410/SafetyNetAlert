package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {


	private List<MedicalRecord> listMedicalRecord = new ArrayList<>();
	
	public void addMedicalRecord(MedicalRecord medicalRecord) {
		this.listMedicalRecord.add(medicalRecord);
	}
	
}

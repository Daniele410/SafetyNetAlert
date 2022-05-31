package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;

public interface IMedicalRecordService {
List <MedicalRecord> getMedicalRecords();

MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord);

}

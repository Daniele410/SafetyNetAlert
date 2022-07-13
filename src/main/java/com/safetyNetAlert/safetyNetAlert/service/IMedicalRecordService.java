package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;

import exception.MedicalRecordNotFoundException;

public interface IMedicalRecordService {
List <MedicalRecord> getMedicalRecords() throws MedicalRecordNotFoundException;

MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord);

MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException ;

MedicalRecord getMedicalRecordByFirstNameAndLastName(String firstName, String lastName);

public boolean isChild(String lastName, String firstName);
}

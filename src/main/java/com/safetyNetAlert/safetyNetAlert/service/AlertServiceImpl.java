package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.repository.FirestationRepository;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

@Service
public class AlertServiceImpl implements IAlertService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FirestationRepository firestationRepository;

	@Autowired
	AgeCalculator ageCalculator;

	public List<String> getCommunityEmail(String city) {
		return personRepository.getPersonsByCity(city).stream().map(p -> p.getEmail()).collect(Collectors.toList());
	}

	public List<PersonInfoDto> getPersonInfo(String firstName, String lastName) {

		return personRepository.getPersonByFirstNameAndLastName(firstName, lastName).stream().map(p -> {
			MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
			int age = ageCalculator.calculate(medicalRecord.getBirthdate());
			return new PersonInfoDto(p.getFirstName(), p.getLastName(), p.getAddress(), age, p.getEmail(),
					medicalRecord.getAllergies(), medicalRecord.getMedications());

		}).collect(Collectors.toList());

	}
	
	

	

}

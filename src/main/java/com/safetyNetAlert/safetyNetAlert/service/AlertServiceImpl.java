package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

/**
 * @author danozzo
 *
 */
@Service
public class AlertServiceImpl implements IAlertService {

	@Autowired
	private IMedicalRecordService medicalRecordService;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IFirestationService firestationService;

	@Autowired
	AgeCalculator ageCalculator;

	public List<String> getCommunityEmail(String city) {
		return personService.getPersonsByCity(city).stream().map(p -> p.getEmail()).collect(Collectors.toList());
	}

	/*
	 * @Override public PersonInfoDto getPersonInfo(String firstName, String
	 * lastName) { return (PersonInfoDto)
	 * personRepository.getPersonByFirstNameAndLastName(firstName,
	 * lastName).stream().map(p -> { MedicalRecord medicalRecord =
	 * medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName); int
	 * age = ageCalculator.calculate(medicalRecord.getBirthdate()); return new
	 * PersonInfoDto(p.getFirstName(), p.getLastName(), p.getAddress(), age,
	 * p.getEmail(), medicalRecord.getAllergies(),
	 * medicalRecord.getMedications());});
	 * 
	 * }
	 */

	// Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les
	// antécédents médicaux (médicaments,
	// posologie, allergies) de chaque habitant. Si plusieurs personnes portent le
	// même nom, elles doivent
	// toutes apparaître

	public PersonInfoDto getPersonInfo(String firstName, String lastName) {
		Person person = personService.getPersonByFirstnameAndLastName(firstName, lastName);
		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(person.getFirstName(),
				person.getLastName());

		PersonInfoDto personInfo = new PersonInfoDto(person.getFirstName(), person.getLastName(), person.getAddress(),
				ageCalculator.calculate(medicalRecord.getBirthdate()), person.getEmail(),
				medicalRecord.getMedications(), medicalRecord.getAllergies());

		return personInfo;
	}
	/*
	 * http://localhost:8080/childAlert?address=<address> Cette url doit retourner
	 * -Une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette
	 * adresse.
	 * -La liste doit comprendre le prénom et le nom de famille de chaque
	 * enfant, son âge et une liste des autres membres du foyer. S'il n'y a pas
	 * d'enfant, cette url peut renvoyer une chaîne vide.
	 */
	@Override
	public ChildDto getChildDto(String address) {
		Person person = personService.getPersonByAddress(address);
		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordsChild(address);
		ChildDto childDto = new ChildDto(person.getFirstName(),person.getLastName(),ageCalculator.calculate(medicalRecord.getBirthdate()),person.getAddress());
		return childDto;
	
	}
	
}

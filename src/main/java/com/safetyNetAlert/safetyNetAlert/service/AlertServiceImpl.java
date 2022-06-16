package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.dto.PhoneDto;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

/**
 * @author danozzo
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

	// Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les
	// antécédents médicaux (médicaments,
	// posologie, allergies) de chaque habitant. Si plusieurs personnes portent le
	// même nom, elles doivent
	// toutes apparaître

	public List<PersonInfoDto> getPersonInfo(String lastName) {
		List<PersonInfoDto> personInfoDtos = new ArrayList<>();
		List<Person> persons = personService.getPersonByLastName(lastName);
		persons.forEach(p -> {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(p.getLastName(),
					p.getFirstName());
			int age = ageCalculator.calculate(medicalRecord.getBirthdate());
			personInfoDtos.add(new PersonInfoDto(p.getFirstName(), p.getLastName(), p.getAddress(), age, p.getEmail(),
					medicalRecord.getMedications(), medicalRecord.getAllergies()));

		});

		return personInfoDtos;
	}

	/*
	 * http://localhost:8080/childAlert?address=<address> Cette url doit retourner
	 * -Une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette
	 * adresse. -La liste doit comprendre le prénom et le nom de famille de chaque
	 * enfant, son âge et une liste des autres membres du foyer. S'il n'y a pas
	 * d'enfant, cette url peut renvoyer une chaîne vide.
	 */
	@Override
	public List<ChildDto> getChildDto(String address) {
		List<ChildDto> childDtos = new ArrayList<>();
		List<Person> persons = personService.getPersonByAddress(address);
		List<Person> childList = persons.stream()
				.filter(p -> medicalRecordService.isChild(p.getLastName(), p.getFirstName()))
				.collect(Collectors.toList());
		childList.forEach(c -> {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(c.getLastName(),
					c.getFirstName());
			int age = ageCalculator.calculate(medicalRecord.getBirthdate());
			List<PersonDto> listeMembersofFamily = persons.stream().filter(
					p -> !(p.getFirstName().equals(c.getFirstName()) && (p.getLastName().equals(c.getLastName()))))
					.map(p -> new PersonDto(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()))
					.collect(Collectors.toList());
			childDtos.add(new ChildDto(c.getFirstName(), c.getLastName(), age, listeMembersofFamily));
		});
		return childDtos;
	}
	/*
	 * http://localhost:8080/phoneAlert?firestation=<firestation_number> Cette url
	 * doit retourner une liste des numéros de téléphone des résidents desservis par
	 * la caserne de pompiers. Nous l'utiliserons pour envoyer des messages texte
	 * d'urgence à des foyers spécifiques.
	 */

	@Override
	public List<PhoneDto> getPhoneAlert(String phone) {

		return null;
	}

}

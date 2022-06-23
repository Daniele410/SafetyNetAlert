package com.safetyNetAlert.safetyNetAlert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.FloodDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonAtAddressDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonLightDto;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
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

	// Retourner une liste d'email de chaque habitants
	public List<String> getCommunityEmail(String city) {
		return personService.getPersonsByCity(city).stream().map(p -> p.getEmail()).distinct()
				.collect(Collectors.toList());
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

	public Set<String> getPersonsPhoneNumberByStation(String firestation) {

		Set<String> setPhoneByStation = firestationService.getFireStationsByStation(firestation).stream()
				.map(Firestation::getAddress)// .map(firestation -> firestation.getAdresse)
				.flatMap(address -> personService.getPersonByAddress(address).stream()).map(Person::getPhone)
				.collect(Collectors.toSet());

//		Deuxième façon de faire =>

//		List<Firestation> listAllFireStationforStation = firestationService.getFireStationsByStation(station);
//		Set<String> listOfAdressesForStation = listAllFireStationforStation.stream().map(Firestation::getAddress).collect(Collectors.toSet());
//		List<Person> listPersonByAdresses = personService.getPersons()
//				.stream()
//				.filter(person -> listOfAdressesForStation.contains(person.getAddress())).collect(Collectors.toList());
//				
//		Set<String> allPhoneNumberByStation = listPersonByAdresses.stream().map(Person::getPhone).collect(Collectors.toSet());

		return setPhoneByStation;
	}
	/*
	 * http://localhost:8080/fire?address=<address> Cette url doit retourner la
	 * liste des habitants vivant à l’adresse donnée ainsi que le numéro de la
	 * caserne de pompiers la desservant. La liste doit inclure le nom, le numéro de
	 * téléphone, l'âge et les antécédents médicaux (médicaments, posologie et
	 * allergies) de chaque personne
	 */

	public List<PersonAtAddressDto> getPersonsByAddressFromListOfStationNumber(String address) {

		List<Person> persons = personService.getPersonByAddress(address); //je cree une liste de person avec address
		Firestation firestation = firestationService.getFirestationsByAddress(address)
				.orElseThrow(() -> new NoSuchElementException("Firestation not found"));

		List<PersonAtAddressDto> personAddressFirestation = persons.stream().map(p -> {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(p.getLastName(),
					p.getFirstName());
			int age = ageCalculator.calculate(medicalRecord.getBirthdate());
			return new PersonAtAddressDto(p.getFirstName(), p.getLastName(), p.getPhone(), firestation.getStation(),
					age, medicalRecord.getMedications(), medicalRecord.getAllergies());
		}).collect(Collectors.toList());
		return personAddressFirestation;

	}

	/*
	 * http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	 * Cette url doit retourner une liste de tous les foyers desservis par la
	 * caserne. Cette liste doit regrouper les personnes par adresse. Elle doit
	 * aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et faire
	 * figurer leurs antécédents médicaux (médicaments, posologie et allergies) à
	 * côté de chaque nom.
	 */

	public List<FloodDto> getPersonsBySameAddress(String station) {

		List<Person> persons = personService.getPersons();
		Firestation firestation = firestationService.getFireStationsByStation(station).orElseThrow(() -> new NoSuchElementException("Firestation not found"));
		
		List<FloodDto> personBySameStation = persons.stream().map(p -> {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(p.getLastName(),
					p.getFirstName());
			int age = ageCalculator.calculate(medicalRecord.getBirthdate());  
			return new FloodDto(p.getLastName(), p.getPhone(), age, firestation.getStation(), medicalRecord.getMedications(), medicalRecord.getAllergies());
		}).collect(Collectors.toList());
		return personBySameStation;
	
	}
	
	/*
	 * http://localhost:8080/firestation?stationNumber=<station_number> Cette url
	 * doit retourner une liste des personnes couvertes par la caserne de pompiers
	 * correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les
	 * habitants couverts par la station numéro 1. La liste doit inclure les
	 * informations spécifiques suivantes : prénom, nom, adresse, numéro de
	 * téléphone. De plus, elle doit fournir un décompte du nombre d'adultes et du
	 * nombre d'enfants (tout individu âgé de 18 ans ou moins) dans la zone
	 * desservie.
	 */
	
	public List<PersonLightDto> getPersonsCoveredByStationNumberWithCountAdultAndChilds(String station) {
		List<Person> persons = personService.getPersons();
		Firestation firestation = firestationService.getFireStationsByStation(station).orElseThrow(() -> new NoSuchElementException("Firestation not found"));
		
		List<PersonLightDto> personBySameStation = persons.stream().map(p -> {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(p.getLastName(),
					p.getFirstName());
			
			return new PersonLightDto(p.getFirstName(), p.getLastName(),p.getAddress(),firestation.getStation() ,p.getPhone(), medicalRecord.getMedications(), medicalRecord.getAllergies());
		}).collect(Collectors.toList());
		return personBySameStation;
	}


	/*
	 * List<Person> persons = personService.getPersons();
	 * firestationsByNumber.stream().map(f->f.getAddress().equals(personService.
	 * getPhoneByAddress(station)));
	 * 
	 * 
	 * List<PhoneDto> phoneNumbersCoveredBySameStationNumber = new ArrayList<>();
	 * List<PhoneDto> phoneDtos= new ArrayList<>();
	 * 
	 * Firestation firestation = new Firestation(); firestation.getAddress();
	 * persons.addAll(personService.getPersonByAddress(station));
	 * 
	 * Person person = new Person(); person.getPhone();
	 * 
	 * 
	 * phoneNumbersCoveredBySameStationNumber.stream().distinct().collect(Collectors
	 * .toList());
	 */

// stream() => je vais parcourir ma liste pour la travailler
// stream().filtrer(person -> person.getAge <= 18)
// stream().map() != Map et != TreeMap
// stream().map() permet de transformer le stream de départ (par exemple une
// liste de person) entre un autre stream ou un objet
// forEach() => personList.forEach(person -> person.setAge(person.getAge()+5)) =
// for (Person person : personList) {person.setAge(person.getAge()+5

// stream()...findFirst()
// stream()...collect(Collectors.toList()) => je récupère ce qu ise passe entre
// les "..." dans une liste
// stream()...collect(Collectors.toSet())
// ...

//	public List<String> getPhoneAlert(String address) {
//		List<Firestation> fireStationsByNumber = firestationService.getFirestations();
//
//		List<Person> persons = new ArrayList<>();
//		List<String> phoneNumbersCoveredBySameStationNumber = new ArrayList<>();
//
//		for (Firestation firestation : fireStationsByNumber) {
//			firestation.getAddress();
//			persons.addAll(personService.getPersonByAddress(address));
//		}
//		for (Person person : persons) {
//			String phoneNumber = person.getPhone();
//
//			phoneNumbersCoveredBySameStationNumber.add(phoneNumber);
//		}
//
//		return phoneNumbersCoveredBySameStationNumber.stream().distinct().collect(Collectors.toList());
//	}

}

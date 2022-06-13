package com.safetyNetAlert.safetyNetAlert.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.FirestationRepository;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;
import com.safetyNetAlert.safetyNetAlert.utils.AgeCalculator;

@Service
public class JsonReaderImpl implements IDataReader {

	private ObjectMapper mapper = new ObjectMapper();

	private String jsonFilePath;

	private JsonNode root = null;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FirestationRepository firestationRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	PersonInfoDto personInfoDto;

	@Autowired
	AgeCalculator ageCalculator;

	public JsonReaderImpl(@Value("${data.jsonFilePath}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	@PostConstruct
	public void readData() throws IOException {
		try {
			root = mapper.readTree(new File(jsonFilePath));
			loadPersons();
			loadFirestations();
			loadMedicalRecords();
			loadPersonInfoDto();
		} catch (FileNotFoundException e) {
		}
	}

	/**
	 * add persons
	 */

	private void loadPersons() {
		JsonNode nodePersons = root.path("persons");
		for (JsonNode nodePerson : nodePersons) {

			Person person = new Person();
			person.setFirstName(nodePerson.path("firstName").asText());
			person.setLastName(nodePerson.path("lastName").asText());
			person.setEmail(nodePerson.path("email").asText());
			person.setAddress(nodePerson.path("address").asText());
			person.setCity(nodePerson.path("city").asText());
			person.setZip(nodePerson.path("zip").asText());
			person.setPhone(nodePerson.path("phone").asText());

			personRepository.addPerson(person);
		}

	}

	/**
	 * add firestations
	 */
	private void loadFirestations() {
		JsonNode nodeFirestations = root.path("firestations");
		for (JsonNode nodeFirestation : nodeFirestations) {

			Firestation firestation = new Firestation();
			firestation.setAddress(nodeFirestation.path("address").asText());
			firestation.setStation(nodeFirestation.path("station").asText());

			firestationRepository.addFirestation(firestation);
		}

	}

	/**
	 * add médicalRecords
	 */

	private void loadMedicalRecords() {
		JsonNode nodeMedicalRecords = root.path("medicalrecords");
		for (JsonNode nodeMedicalRecord : nodeMedicalRecords) {

			MedicalRecord medicalRecord = new MedicalRecord();
			try {
				medicalRecord = mapper.treeToValue(nodeMedicalRecord, MedicalRecord.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			medicalRecordRepository.addMedicalRecord(medicalRecord);

		}
	}

	private void loadPersonInfoDto() {
		JsonNode nodePersonInfoDtos = root.path("personInfo");
		for (JsonNode nodePersonInfoDto : nodePersonInfoDtos) {

			PersonInfoDto personInfoDto = new PersonInfoDto();
			try {
				personInfoDto = mapper.treeToValue(nodePersonInfoDto, PersonInfoDto.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			personInfoDto.addPersonInfoDto(personInfoDto);

		}
	}
			
	
}

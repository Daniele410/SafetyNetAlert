package com.safetyNetAlert.safetyNetAlert.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.FirestationRepository;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

@Service
public class JsonReaderImpl implements IDataReader {
	
	private static final Logger logger = LogManager.getLogger("JsonReaderImpl");

	private ObjectMapper mapper = new ObjectMapper();

	private String jsonFilePath;

	private JsonNode root = null;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FirestationRepository firestationRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;


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
		} catch (FileNotFoundException e) {
			logger.error("File not found");
		}
	}

	/**
	 * load persons
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
	 * load firestations
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
	 * load médicalRecords
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
	/**
	 * clearData => clear all data in repository
	 * 
	 * @throws IOException
	 */
	public void clearData() throws IOException {
		personRepository.getAllPersons().clear();
		firestationRepository.getAllFirestation().clear();
		medicalRecordRepository.getAllMedicalRecords().clear();
	}

}

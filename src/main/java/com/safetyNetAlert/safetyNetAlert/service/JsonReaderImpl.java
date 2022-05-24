package com.safetyNetAlert.safetyNetAlert.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.repository.FirestationRepository;
import com.safetyNetAlert.safetyNetAlert.repository.MedicalRecordRepository;
import com.safetyNetAlert.safetyNetAlert.repository.PersonRepository;

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
	
	public JsonReaderImpl(@Value("${data.jsonFilePath}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}
	
	@PostConstruct
	public void readData() throws IOException {
		try {
			root = mapper.readTree(new File(jsonFilePath));
			loadPersons();
			loadFirestation();
			loadMedicalRecord();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	private void loadPersons() {
		JsonNode nodePersons = root.path("persons");
		for (JsonNode nodePerson : nodePersons) {
			System.out.println("récupération de la personne " +nodePerson.path("firstName").asText() );
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

	private void loadMedicalRecord() {
		// TODO Auto-generated method stub
		
	}

	private void loadFirestation() {
		// TODO Auto-generated method stub
		
	}


}
		

		

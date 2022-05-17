package com.safetyNetAlert.safetyNetAlert.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonReaderImpl {
	
	private ObjectMapper objectMapper;
	@PostConstruct
	public void doSomethingWhenApplicationStart() {
		System.out.println("je suis dans la méthode dosomething");
		//Lire le fichier Json, comment récupérer la donnée d'un fichier
		
		//extraire les persons
		loadPersons(nodePersons)
		//extraire les firestations
		//extraires les medicalRecords
	}

}

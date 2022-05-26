package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.repository.FirestationRepository;

@RestController
public class FirestationController {
	
	@Autowired
	private FirestationRepository firestationRepository;
	
	@GetMapping(value= "/firestation")
	public ResponseEntity<List<Firestation>>getAllFirestations(){
		return new ResponseEntity<>(firestationRepository.getAllFirestation(), HttpStatus.OK);
				
	}
	
	@PostMapping(value= "/firestation")
	public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation) {
		firestationRepository.addFirestation(firestation);
		return new ResponseEntity<>(firestation, HttpStatus.CREATED);
	}
	

}

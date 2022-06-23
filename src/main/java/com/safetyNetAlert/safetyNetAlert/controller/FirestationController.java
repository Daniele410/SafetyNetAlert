package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.service.IFirestationService;

@RestController
public class FirestationController {
	
	private static final Logger logger = LogManager.getLogger("FirestationController");
	
	@Autowired
	private IFirestationService firestationService;
	
	@GetMapping(value= "/firestation")
	public ResponseEntity<List<Firestation>>getAllFirestations(){
		List<Firestation> firestationsRecordsList = firestationService.getFirestations();
		logger.info("GET request for firestation send");
		logger.info("Response for the GET request for firestation: " + firestationsRecordsList);
		return new ResponseEntity<>(firestationService.getFirestations(), HttpStatus.OK);
	}
	
	@PostMapping(value= "/firestation")
	public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation) {
		firestationService.addFirestation(firestation);
		return new ResponseEntity<>(firestation, HttpStatus.CREATED);
	}
	
	 @PutMapping(value = "/firestation")
	    public ResponseEntity<Firestation> updatePerson( @RequestBody Firestation firestation) {
	        return new ResponseEntity<>(firestationService.updateFirestation(firestation), HttpStatus.OK);
	    }
	 
	 @DeleteMapping(value = "/firestation")
		public ResponseEntity<Firestation> deletePerson(@RequestBody Firestation firestation) {
			return new ResponseEntity<>(firestationService.deleteFirestation(firestation), HttpStatus.OK);
		}
	

}

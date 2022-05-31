package com.safetyNetAlert.safetyNetAlert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.service.IFirestationService;

@RestController
public class FirestationController {
	
	
	
	@Autowired
	private IFirestationService iFirestationService;
	
	@GetMapping(value= "/firestation")
	public ResponseEntity<List<Firestation>>getAllFirestations(){
		return new ResponseEntity<>(iFirestationService.getFirestations(), HttpStatus.OK);
	}
	
	@PostMapping(value= "/firestation")
	public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation) {
		iFirestationService.addFirestation(firestation);
		return new ResponseEntity<>(firestation, HttpStatus.CREATED);
	}
	
	 @PutMapping(value = "/firestation")
	    public ResponseEntity<Firestation> updatePerson( @RequestBody Firestation firestation) {
	        return new ResponseEntity<>(iFirestationService.updateFirestation(firestation), HttpStatus.OK);
	    }
	

}

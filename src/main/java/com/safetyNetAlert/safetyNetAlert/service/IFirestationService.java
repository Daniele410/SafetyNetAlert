package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.Optional;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

import exception.FirestationNotFoundException;

public interface IFirestationService {
	List<Firestation> getFirestations() throws FirestationNotFoundException;

	Firestation deleteFirestation(Firestation firestation);

	Firestation addFirestation(Firestation firestation);

	Firestation updateFirestation(Firestation firestation) throws FirestationNotFoundException;

	public Optional<Firestation> getFirestationsByAddress(String address);
	
	List<String> getAddressesCoveredByStationNumber(String firestation);
	
	List<Firestation> getFireStationsByStation(String station) ;

	
}

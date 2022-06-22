package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.Optional;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

public interface IFirestationService {
	List<Firestation> getFirestations();

	Firestation deleteFirestation(Firestation firestation);

	Firestation addFirestation(Firestation firestation);

	Firestation updateFirestation(Firestation firestation);

	public Optional<Firestation> getFirestationsByAddress(String address);
	
	List<Firestation> getAddressesCoveredByStationNumber(String firestation);
	
	Optional<Firestation> getFireStationsByStation(String station) ;

	
}

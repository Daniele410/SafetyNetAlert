package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

public interface IFirestationService {
	List<Firestation> getFirestations();

	Firestation deleteFirestation(Firestation firestation);

	Firestation addFirestation(Firestation firestation);

	Firestation updateFirestation(Firestation firestation);
	
	Firestation getAddressByStationNumber (String stationNumber);

	
	
	public List<Firestation> getFirestationsByAddress(String address);
}

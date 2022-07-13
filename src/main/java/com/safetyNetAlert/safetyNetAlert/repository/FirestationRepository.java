package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

import exception.FirestationNotFoundException;

@Repository
public class FirestationRepository {
	
	static final Logger logger = LogManager.getLogger(FirestationRepository.class);

	private List<Firestation> listFirestation = new ArrayList<>();

	public FirestationRepository(List<Firestation> firestations) {
		this.listFirestation.addAll(firestations);
	}

	public void addFirestation(Firestation firestation) {
		this.listFirestation.add(firestation);
	}

	public List<Firestation> getAllFirestation() {
		return this.listFirestation;
	}

	public Firestation updateFirestation(Firestation firestation) {
		logger.debug("updating firestation {}", firestation);
		Firestation firestatiToUpdate = findByAddress(firestation.getAddress());
		if (firestatiToUpdate != null) {
			deleteFirestation(firestatiToUpdate);
			addFirestation(firestation);
			return firestation;
		}else
		return null;
//		Firestation firestationToUpdate = findByAddress(firestation.getAddress());
//		int index = listFirestation.indexOf(firestationToUpdate);
//		// mise à jour de la firestation grâce à l'index dans la liste
//		listFirestation.set(index, firestation);
//		return firestationToUpdate;
	}

	public void deleteFirestation(Firestation firestation) {
		Firestation firestationToDelete = findByAddressAndStation(firestation.getAddress(),firestation.getStation());
		int index = listFirestation.indexOf(firestationToDelete);
		listFirestation.remove(index);
		
		}

	private Firestation findByAddressAndStation(String address, String station) {

		Optional<Firestation> firestationToFind = listFirestation.stream().filter(
				firestation -> (firestation.getAddress().equals(address)) && (firestation.getStation().equals(station)))
				.findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			return this.findByAddressAndStation(address, station);
	}
	
	public Firestation findByAddress(String address) {

		Optional<Firestation> firestationToFind = listFirestation.stream().filter(
				firestation -> (firestation.getAddress().equals(address)))
				.findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			return null;
	}
	
	
	public Firestation getAddressByStationNumber (String stationNumber) throws FirestationNotFoundException  {
		Optional<Firestation> firestationToFind = listFirestation.stream()
				.filter(listFirestation -> listFirestation.getStation().equals(stationNumber)).findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			throw new FirestationNotFoundException(
					"firestation " + stationNumber + " not found");
	}
	
	public Optional<Firestation> getFirestationsByAddress(String address){
		return this.listFirestation.stream().filter(firestation -> firestation.getAddress().equals(address)).findAny();
		
	} 
	
	public List<String> getAddressesCoveredByStationNumber (String station){
		return this.listFirestation.stream().filter(firestation -> firestation.getStation().equals(station)).map(Firestation::getAddress ).collect(Collectors.toList());
	}

public List<Firestation> getFireStationsByStation(String station) {
		return listFirestation.stream().filter(firestation -> firestation.getStation().equals(station)).collect(Collectors.toList());
	}



}

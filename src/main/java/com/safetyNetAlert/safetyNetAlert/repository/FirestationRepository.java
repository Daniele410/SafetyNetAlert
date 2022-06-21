package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

@Repository
public class FirestationRepository {

	private List<Firestation> listFirestation = new ArrayList<>();

	public void addFirestation(Firestation firestation) {
		this.listFirestation.add(firestation);
	}

	public List<Firestation> getAllFirestation() {
		return this.listFirestation;
	}
	
	
	

	public void updateFirestation(Firestation firestation) {

		Firestation firestationToUpdate = findByAddress(firestation.getAddress());

		int index = listFirestation.indexOf(firestationToUpdate);
		// mise à jour de la personne grâce à l'index dans la liste
		listFirestation.set(index, firestation);
	}

	public void deleteFirestation(Firestation firestation) {
		Firestation firestationToDelete = findByAddressAndStation(firestation.getAddress(),firestation.getStation());
		int index = listFirestation.indexOf(firestationToDelete);
		listFirestation.remove(index);
		//cas ou firestation avec adresse = null et station != null => on supprime toutes les firestations
		if (firestation.getAddress() == null && firestation.getStation()!=null) {
			listFirestation.remove(index);
		}
		//cas ou firestation avec adresse != null et station != null =>
		else if(firestation.getAddress() != null && firestation.getStation()!=null) {
			listFirestation.remove(index);
		// cas ou firestation avec adresse != null et station = null =>
		} else {listFirestation.remove(index);}
		}

	private Firestation findByAddressAndStation(String address, String station) {

		Optional<Firestation> firestationToFind = listFirestation.stream().filter(
				firestation -> (firestation.getAddress().equals(address)) && (firestation.getStation().equals(station)))
				.findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			return null;
	}
	
	private Firestation findByAddress(String address) {

		Optional<Firestation> firestationToFind = listFirestation.stream().filter(
				firestation -> (firestation.getAddress().equals(address)))
				.findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			return null;
	}
	
	
	public Firestation getAddressByStationNumber (String stationNumber) {
		Optional<Firestation> firestationToFind = listFirestation.stream()
				.filter(listFirestation -> listFirestation.getStation().equals(stationNumber)).findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			return null;
	}
	
	public List<Firestation> getFirestationsByAddress(String address){
		return this.listFirestation.stream().filter(firestation -> firestation.getAddress().equals(address)).collect(Collectors.toList());
		
	} 
	
	public List<Firestation> getAddressesCoveredByStationNumber (String station){
		return this.listFirestation.stream().filter(firestation -> firestation.getStation().equals(station)).collect(Collectors.toList());
	}

public List<Firestation> getFireStationsByStation(String station) {
		return listFirestation.stream().filter(firestation -> firestation.getStation().equals(station)).collect(Collectors.toList());
	}

}

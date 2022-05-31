package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

@Repository
public class FirestationRepository {

	private List<Firestation> listFirestation = new ArrayList<>();

	public void addFirestation(Firestation firestation) {
		this.listFirestation.add(firestation);
	}
	
	public List<Firestation> getAllFirestation(){
		return this.listFirestation;
	}
	
	public void updateFirestation(Firestation firestation) {

		Firestation firestationToUpdate = findByAddressAndStation(firestation.getAddress(),
				firestation.getStation());

		int index = listFirestation.indexOf(firestationToUpdate);
		// mise à jour de la personne grâce à l'index dans la liste
		listFirestation.set(index, firestation);
	}

	private Firestation findByAddressAndStation(String address, String station) {

		Optional<Firestation> firestationToFind = listFirestation.stream()
				.filter(firestation -> (firestation.getAddress().equals(address))
						&& (firestation.getStation().equals(station)))
				.findFirst();
		if (firestationToFind.isPresent()) {
			return firestationToFind.get();
		} else
			return null;
	}

}

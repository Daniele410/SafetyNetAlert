package com.safetyNetAlert.safetyNetAlert.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

@Repository
public class FirestationRepository {

	private List<Firestation> listFirestation = new ArrayList<>();

	public void addFirestation(Firestation firestation) {
		this.listFirestation.add(firestation);
	}

}

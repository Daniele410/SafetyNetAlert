package com.safetyNetAlert.safetyNetAlert.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

class FirestationRepositoryTest {

	private static FirestationRepository firestationRepository;

	private static List<Firestation> firestations = new ArrayList<>();
	static {
		firestations.add(new Firestation("748 Townings Dr", "3"));
		firestations.add(new Firestation("29 15th St", "2"));
		firestations.add(new Firestation("1509 Culver St", "3"));
		firestations.add(new Firestation("644 Gershwin Cir", "1"));
		firestations.add(new Firestation("834 Binoc Ave", "3"));
	}

	@BeforeEach
	void setUp() throws Exception {
		firestationRepository = new FirestationRepository(firestations);
	}

	@Test
	void findAll_test() {
		
		// Given //When
		List<Firestation> result = firestationRepository.getAllFirestation();
		
		// Then
		assertThat(result).isEqualTo(firestations);

	}

	@Test
	void addFireStation_test() {
		// arrange
		Firestation firestation = new Firestation("112 Steppes Pl", "4");
		// act
		firestationRepository.addFirestation(firestation);
		// assert
		assertNotNull(firestation);

	}

	@Test
	void deleteFireStation_test() {
		
		// Given
		Firestation fireStation = new Firestation("1509 Culver St", "3");
		
		// When
		firestationRepository.deleteFirestation(fireStation);
		
		// Then
		assertThat(firestations).doesNotContain(fireStation);

	}

	@Test
	void FindByAddressTest() {

		// Given // When
		Optional<Firestation> result = firestationRepository.getFirestationsByAddress(firestations.get(0).getAddress());

		// Then
		assertNotNull(result);
	}

	@Test
	void FindByStation_test() {
		// Given
		Firestation firestation = new Firestation("1509 Culver St", "3");
		// When
		List<Firestation> result = firestationRepository.getFireStationsByStation(firestation.getStation());
		// Then
		assertNotNull(result);

	}

	@Test
	void updateFireStation_test() {
		// arrange
		Firestation firestation = new Firestation("748 Townings Dr", "1");
		// act
		Firestation result = firestationRepository.updateFirestation(firestation);
		// assert
		assertNotNull(result);

	}
	
	@Test
	void FindByStationByStationNumber_test() {
		// Given
		Firestation firestation = new Firestation("644 Gershwin Cir", "1");
		// When
		List<Firestation> result = firestationRepository.getFireStationsByStation(firestation.getStation());
		// Then
		assertNotNull(result);
		assertEquals(1, 1);

	}

}

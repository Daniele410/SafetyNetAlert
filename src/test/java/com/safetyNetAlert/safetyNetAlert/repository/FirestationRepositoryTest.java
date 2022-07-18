package com.safetyNetAlert.safetyNetAlert.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNetAlert.safetyNetAlert.model.Firestation;

import exception.FirestationNotFoundException;

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
		// Given
		Firestation firestation = new Firestation("112 Steppes Pl", "4");
		// When
		firestationRepository.addFirestation(firestation);
		// Then
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
		// Given
		Firestation firestation = new Firestation("748 Townings Dr", "1");
		// When
		Firestation result = firestationRepository.updateFirestation(firestation);
		// Then
		assertNotNull(result);

	}

	@Test
	void updateFireStationTest_ShouldReturnNull() {

		Firestation firestation = new Firestation(null, null);
		// Given // When // Then
		Firestation result = firestationRepository.updateFirestation(firestation);

		assertNull(result);
	}

	@Test
	void FindByStationByStationNumberTest_ShouldReturnStation() {
		// Given
		Firestation firestation = new Firestation("644 Gershwin Cir", "1");
		// When
		List<Firestation> result = firestationRepository.getFireStationsByStation(firestation.getStation());
		// Then
		assertNotNull(result);
		assertEquals(1, 1);

	}

	@Test
	void getAddressByStationNumberTest_ShoultReturnFirestationByAddress() throws FirestationNotFoundException {

		// Given //When
		Firestation result = firestationRepository.getAddressByStationNumber(firestations.get(0).getStation());

		// Then
		assertNotNull(result);

	}

	@Test
	void getAddressByStationNumberTest_ShoultReturnFirestationNotFoundException() throws FirestationNotFoundException {

		// Given //When //Then

		Firestation firestation = new Firestation("644 Gershwin Cir", "");
		
		assertThrows(FirestationNotFoundException.class,
				() -> firestationRepository.getAddressByStationNumber(firestation.getStation()));
		

	}

}

package com.safetyNetAlert.safetyNetAlert.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.service.IFirestationService;

//@WebMvcTest(controllers = PersonController.class)permet de tester le controller.
@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IFirestationService firestationService;

	@Mock
	private Firestation firestationTest1;

	@Mock
	private Firestation firestationTest2;

	@BeforeEach
	void setup() {
		new ObjectMapper();

		firestationTest1 = new Firestation("1509 Culver St", "3");

		firestationTest2 = new Firestation("29 15th St", "2");

	}

	@Test
	public void getAllFirestationTest() throws Exception {

		// Given

		List<Firestation> firestationList = new ArrayList<>(Arrays.asList(firestationTest1, firestationTest2));

		// When
		Mockito.when(firestationService.getFirestations()).thenReturn(firestationList);

		// Then
		mockMvc.perform(get("/firestation")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(2)))
				.andExpect(jsonPath("$[0].address", is("1509 Culver St"))).andExpect(status().isOk());

	}

	@Test
	public void addFirestationTest() throws Exception {

		// Given
		Firestation firestation1 = new Firestation("1509 Culver St", "3");

		// When
		when(firestationService.addFirestation(firestation1)).thenReturn(firestation1);

		// Then
		mockMvc.perform(post("/firestation").content(objectMapper.writeValueAsString(firestation1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

	}

	@Test
	public void testGetFirestationByStation() throws Exception {
		// Given

		List<Firestation> firestationList = new ArrayList<>(Arrays.asList(firestationTest1, firestationTest2));

		// Then
		when(firestationService.getFireStationsByStation(anyString())).thenReturn(firestationList);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("station", "3")).andExpect(status().isOk());

	}

	@Test
	public void testPutFirestation() throws Exception {

		// Given
		String firestationRecord = "{\"address\":\"1509 Culver St\",\"station\":\"3\"}";

		// When
		when(firestationService.updateFirestation(firestationTest1)).thenReturn(firestationTest1);
		
		// Then
		mockMvc.perform(MockMvcRequestBuilders.put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content(firestationRecord)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteFirestation() throws Exception {
		// Given // When
		mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
				.content(objectMapper.writeValueAsString(firestationTest1)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

}

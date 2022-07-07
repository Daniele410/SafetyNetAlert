package com.safetyNetAlert.safetyNetAlert.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.service.IAlertService;

//@WebMvcTest(controllers = AlertController.class)permet de tester le controller.
@WebMvcTest(controllers = AlertController.class)
class AlertControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IAlertService alertService;

	@BeforeEach
	void setup() {
		new ObjectMapper();

	}

	// Format test
	// Given
	// When
	// Then
	
	@Test
	public void getPersonsCoveredByStation() throws Exception {

		String stationNumber = "1";
		when(alertService.getPersonsCoveredByStation(stationNumber)).thenReturn(any());

		mockMvc.perform(MockMvcRequestBuilders.get("/firestations").contentType(MediaType.APPLICATION_JSON)
				.param("stationNumber", "1")).andExpect(status().isOk());

	}

	@Test
	public void getPersonsByAddressFromListOfStationNumber() throws Exception {

		Firestation firestation = new Firestation("1509 Culver St", "1");
		when(alertService.getPersonsByAddressFromListOfStationNumber(firestation.getStation())).thenReturn(any());
		mockMvc.perform(MockMvcRequestBuilders.get("/fire").contentType(MediaType.APPLICATION_JSON).param("address",
				"1509 Culver St")).andExpect(status().isOk());

	}

	@Test
	public void getPersonsBySameAddress() throws Exception {

		when(alertService.getPersonsBySameAddress(anyString())).thenReturn(any());

		mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations").contentType(MediaType.APPLICATION_JSON)
				.param("stations", "1")).andExpect(status().isOk());

	}

	@Test
	public void getPersonInfoTest() throws Exception {

		when(alertService.getPersonInfo(anyString())).thenReturn(any());
		mockMvc.perform(MockMvcRequestBuilders.get("/personInfo").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Toto").param("lastName", "Tutu")).andExpect(status().isOk());

	}

	@Test
	public void getChildByAddressTest() throws Exception {
		when(alertService.getChildDto(anyString())).thenReturn(any());

		mockMvc.perform(MockMvcRequestBuilders.get("/childAlert").contentType(MediaType.APPLICATION_JSON)
				.param("address", "29 15th St")).andExpect(status().isOk());

	}

	@Test
	public void getPersonsPhoneNumberByStation() throws Exception {
		when(alertService.getPersonsPhoneNumberByStation(anyString())).thenReturn(any());

		mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert").contentType(MediaType.APPLICATION_JSON)
				.param("firestation", "2")).andExpect(status().isOk());

	}

	@Test
	public void getCommunityEmail() throws Exception {

		when(alertService.getCommunityEmail(anyString())).thenReturn(any());

		mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail").contentType(MediaType.APPLICATION_JSON)
				.param("city", "Culver")).andExpect(status().isOk());

	}
	
	


}

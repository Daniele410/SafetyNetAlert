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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.MedicalRecord;
import com.safetyNetAlert.safetyNetAlert.service.IMedicalRecordService;

//@WebMvcTest(controllers = MedicalRecordController.class)permet de tester le controller.
@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IMedicalRecordService medicalRecordService;

	
	private MedicalRecord medicalRecordTest1;

	
	

	@BeforeEach
	void setup() {
		new ObjectMapper();

		medicalRecordTest1 = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("nillacilan"));

		
	}

	@Test
	public void getAllMedicalRecordTest() throws Exception {

		// Given
		List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(medicalRecordTest1));

		// When
		Mockito.when(medicalRecordService.getMedicalRecords()).thenReturn(medicalRecordList);

		// Then
		mockMvc.perform(get("/medicalRecord")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Jimmy")));

	}

	@Test
	public void addMedicalRecordTest() throws Exception {

		// Given
		MedicalRecord medicalRecord1 = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("peanut"));

		// When
		when(medicalRecordService.addMedicalRecord(medicalRecord1)).thenReturn(medicalRecord1);

		// Then
		mockMvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(medicalRecord1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

	}

	@Test
	public void testGetMedicalRecord() throws Exception {
		// Given
		MedicalRecord medicalRecord1 = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("peanut"));

		// Then
		when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(medicalRecord1);

		// Then
		mockMvc.perform(
				MockMvcRequestBuilders.get("/medicalRecord").param("firstName", "Jimmy").param("lastName", "Sax"))
				.andExpect(status().isOk());

	}

	
	
	@Disabled
	@Test
	public void testPutMedicalRecord() throws Exception {

		// Given
		String medicalRecord = "{\"firstName\":\"Jimmy\",\"lastName\":\"Sax\",\"birthdate\":\"03/06/1984\",\"medications\":\"aznol:350mg\",\"allergies\":\"shellfish\"}";

		MedicalRecord medicalRecord1 = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
				Arrays.asList("peanut"));
		List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(medicalRecord1));
		// When
		when(medicalRecordService.addMedicalRecord(medicalRecord1)).thenReturn(medicalRecord1);

		mockMvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(medicalRecordList))

				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
		when(medicalRecordService.updateMedicalRecord(medicalRecordTest1)).thenReturn(medicalRecordTest1);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Jimmy").param("lastName", "Sax").content(medicalRecord))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		// Given // When // Then

		mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
				.content(objectMapper.writeValueAsString(medicalRecordTest1)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
}

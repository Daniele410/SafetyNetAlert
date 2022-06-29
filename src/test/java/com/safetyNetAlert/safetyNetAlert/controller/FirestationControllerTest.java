package com.safetyNetAlert.safetyNetAlert.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.Firestation;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.service.IFirestationService;
import com.safetyNetAlert.safetyNetAlert.service.IPersonService;

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
	

}

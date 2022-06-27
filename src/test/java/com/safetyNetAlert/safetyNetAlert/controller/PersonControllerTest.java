package com.safetyNetAlert.safetyNetAlert.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.service.IPersonService;

//@WebMvcTest(controllers = PersonController.class)permet de tester le controller.
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IPersonService personService;

//	@BeforeEach
//	void setupTest() {
//		Person personTest = new Person("Guix", "DeBrens", "150 Rue Houdan", "Sceaux,", "92330", "0630031876",
//				"guix92@hotmail.com");
//	}

	// Format test
	// Given
	// When
	// Then

	@Test
	public void getAllPersonTest() throws Exception {

		// Given
		Person person1 = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"toto@gmail.com");
		Person person2 = new Person("Felicia", "Cooper", "112 Steppes Pl", "Ghulja", "97451", "841-874-6874",
				"memet99@gmail.com");
		List<Person> personList = new ArrayList<>(Arrays.asList(person1, person2));

		// When
		Mockito.when(personService.getPersons()).thenReturn(personList);
		mockMvc.perform(get("/person")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(2)))
				.andExpect(jsonPath("$[0].firstName", is("Toto")));

		// Then
		verify(personService).getPersons();

	}

	@Test
	public void addPersonTest() throws Exception {
		// Given
		Person personTest = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"toto@gmail.com");

		// When
		when(personService.addPerson(personTest)).thenReturn(personTest);

		// Then
		mockMvc.perform(post("/person").content(objectMapper.writeValueAsString(personTest))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

	}

////	@Test
////
////	public void testDeletePerson() throws Exception {
////
////		Person personTest = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
////				"toto@gmail.com");
////
////		mockMvc.perform(MockMvcRequestBuilders.delete("/person").param("firstName", "Toto").param("lastName", "Tutu"))
////				.andExpect(status().isOk());
////
////		verify(personService).deletePerson(personTest);
////	}
//	

	@Test
	public void TestGetPerson() throws Exception {
		Person personTest = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"toto@gmail.com");

		when(personService.getPersonByFirstnameAndLastName(anyString(), anyString())).thenReturn(personTest);

		mockMvc.perform(MockMvcRequestBuilders.get("/person/").param("firstName", "Toto").param("lastName", "Tutu"))
				.andExpect(status().isOk());

	}
	

	@Test
	public void testPutPerson() throws Exception {

		Person personTest = new  Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"toto@gmail.com");
		when(personService.updatePerson(personTest)).thenReturn(personTest);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/person").contentType(MediaType.APPLICATION_JSON).contentType(anyString()))
				.andExpect(status().isOk());
	}

}

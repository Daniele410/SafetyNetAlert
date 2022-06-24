package com.safetyNetAlert.safetyNetAlert.controller;

import static org.hamcrest.CoreMatchers.is;
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
		Person person = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"toto@gmail.com");

		// When
		when(personService.addPerson(person)).thenReturn(person);

		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(person))).andExpect(status().isCreated());

		// Then
		verify(personService).addPerson(person);

	}

	@Test
	public void updateAPersonTest() throws Exception {

	}

}

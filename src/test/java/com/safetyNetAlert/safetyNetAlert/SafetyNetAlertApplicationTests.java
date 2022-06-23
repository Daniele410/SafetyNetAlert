package com.safetyNetAlert.safetyNetAlert;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetyNetAlert.safetyNetAlert.model.Person;
import com.safetyNetAlert.safetyNetAlert.service.IPersonService;

@SpringBootTest
class SafetyNetAlertApplicationTests {

	@Autowired
	private IPersonService personRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}
	
	
	// Given/When/Then format

	@Test
	public void givenPersons_whemGetAllPersons_thenListOfStudent() {
	
		//given - setup or precondition
		List<Person> persons = personRepository.getPersons();
		
		
		
		//when - action
		
		
		
		
		//then - verify the output
		
		
		
}
	
	
	
	
	
}

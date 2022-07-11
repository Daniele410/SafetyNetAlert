package com.safetyNetAlert.safetyNetAlert.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AgeCalculatorTest {

	@Autowired
	AgeCalculator ageCalculator= new AgeCalculator();
	
	@Test
	void calculateTest_shouldReturnAgeToBirth() {
		
		// Given
		String birthdate = "11/16/1990";
		
		// When
		int result = ageCalculator.calculate(birthdate);
		
		// Then
		assertThat(result).isEqualTo(31);
	}

}

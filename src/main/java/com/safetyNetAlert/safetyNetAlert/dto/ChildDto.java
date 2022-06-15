package com.safetyNetAlert.safetyNetAlert.dto;

public class ChildDto {

	private String firstName;
	private String lastName;
	private int birthdate;
	
	
	
	
	public ChildDto(String firstName, String lastName, int birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
}
	
	
	
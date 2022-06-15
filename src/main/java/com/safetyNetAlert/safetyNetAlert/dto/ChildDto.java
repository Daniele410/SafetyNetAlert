package com.safetyNetAlert.safetyNetAlert.dto;

public class ChildDto {

	private String firstName;
	private String lastName;
	private int birthdate;
	private String address;
	
	
	
	
		
	
	
	public ChildDto(String firstName, String lastName, int birthdate, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
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


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}

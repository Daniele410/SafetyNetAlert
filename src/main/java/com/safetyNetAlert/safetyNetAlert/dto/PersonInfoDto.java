package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

public class PersonInfoDto  {


	private String firstName;
	private String lastName;
	private String address;
	private int birthdate;
	private String email;
	private List<String> medications;
	private List<String> allergies;
	
	
	
	
	public PersonInfoDto(String firstName, String lastName, String address, int birthdate, String email,
			List<String> medications, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthdate = birthdate;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	
	
	
	
}
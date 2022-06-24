package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

public class PersonLightDto {
	private String firstName;
	private String lastName;
	private String address;
	private String station;
	private String phone;
	private List<String> medications;
	private List<String> allergies;
	private int age;


	public PersonLightDto(String firstName, String lastName, String address, String station, String phone,
			List<String> medications, List<String> allergies, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.station = station;
		this.phone = phone;
		this.medications = medications;
		this.allergies = allergies;
		this.age = age;

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

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	/**
	 * @return the adultAge
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param adultAge the adultAge to set
	 */
	public void setAge(int adultAge) {
		this.age = adultAge;
	}


}

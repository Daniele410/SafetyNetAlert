package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

public class ChildDto {

	private String firstName;
	private String lastName;
	private int age;
	private List<PersonDto> membersOfFamily;
	
	
	
	
	public ChildDto(String firstName, String lastName, int age, List<PersonDto> membersOfFamily) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.membersOfFamily = membersOfFamily;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<PersonDto> getMembersOfFamily() {
		return membersOfFamily;
	}
	public void setMembersOfFamily(List<PersonDto> membersOfFamily) {
		this.membersOfFamily = membersOfFamily;
	}

	
	

}

package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.model.Person;

public class ChildDto {

	private String firstName;
	private String lastName;
	private int age;
	private List<Person> listeMembersOfFamily;
	
	
	public ChildDto(String firstName, String lastName, int age, List<Person> listeMembersOfFamily) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.listeMembersOfFamily = listeMembersOfFamily;
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
	public List<Person> getListeMembersOfFamily() {
		return listeMembersOfFamily;
	}
	public void setListeMembersOfFamily(List<Person> listeMembersOfFamily) {
		this.listeMembersOfFamily = listeMembersOfFamily;
	}
	
	
	
}

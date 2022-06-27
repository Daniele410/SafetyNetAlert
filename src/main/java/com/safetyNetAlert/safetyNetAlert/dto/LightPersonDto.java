package com.safetyNetAlert.safetyNetAlert.dto;

public class LightPersonDto {

	private String firstname;
	private String lastname;
	private String address;
	private String phone;
	private int age;
	
	
	
	
	
	public LightPersonDto(String firstname, String lastname, String address, String phone, int age) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phone = phone;
		this.age = age;
	}
	public LightPersonDto() {
		// TODO Auto-generated constructor stub
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

	
	
	
}

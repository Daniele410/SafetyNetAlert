package com.safetyNetAlert.safetyNetAlert.dto;

public class PhoneDto {
	String address;
	String phone;
	
	public PhoneDto(String address, String phone) {
		super();
		this.address = address;
		this.phone = phone;
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

	@Override
	public String toString() {
		return "PhoneDto [address=" + address + ", phone=" + phone + "]";
	}

	
	
	 
	

	
	
	
}

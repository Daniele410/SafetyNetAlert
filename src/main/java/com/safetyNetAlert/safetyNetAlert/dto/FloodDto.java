package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

public class FloodDto {

	private String address;
	private List<HouseHolderDto> holderDtos;
	
	
	
	
	public FloodDto(String address, List<HouseHolderDto> holderDtos) {
		this.address = address;
		this.holderDtos = holderDtos;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<HouseHolderDto> getHolderDtos() {
		return holderDtos;
	}
	public void setHolderDtos(List<HouseHolderDto> holderDtos) {
		this.holderDtos = holderDtos;
	}
	
	
	
	
	
}

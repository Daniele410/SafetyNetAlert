package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.Set;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.FloodDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonAtAddressDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonByFirestationDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;

import exception.PersonNotFoundException;

public interface IAlertService {

	public List<String> getCommunityEmail(String city) throws PersonNotFoundException;

	public List<PersonInfoDto> getPersonInfo(String lastName) throws PersonNotFoundException;

	public List<ChildDto> getChildDto(String address);

	public Set<String> getPersonsPhoneNumberByStation(String station);
	
	public List<PersonAtAddressDto> getPersonsByAddressFromListOfStationNumber(String address);
	
	public List<FloodDto> getPersonsBySameAddress(String firestation);
	
	public PersonByFirestationDto getPersonsCoveredByStation(String stationNumber);
	
	

}

package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.Set;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.FloodDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonAtAddressDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonByFirestationDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;

import exception.FirestationNotFoundException;
import exception.PersonNotFoundException;

public interface IAlertService {

	public List<String> getCommunityEmail(String city) throws PersonNotFoundException;

	public List<PersonInfoDto> getPersonInfo(String lastName) throws PersonNotFoundException;

	public List<ChildDto> getChildDto(String address) throws PersonNotFoundException;

	public Set<String> getPersonsPhoneNumberByStation(String station) throws PersonNotFoundException;
	
	public List<PersonAtAddressDto> getPersonsByAddressFromListOfStationNumber(String address) throws PersonNotFoundException, FirestationNotFoundException;
	
	public List<FloodDto> getPersonsBySameAddress(String firestation) throws PersonNotFoundException;
	
	public PersonByFirestationDto getPersonsCoveredByStation(String stationNumber) throws PersonNotFoundException;
	
	

}

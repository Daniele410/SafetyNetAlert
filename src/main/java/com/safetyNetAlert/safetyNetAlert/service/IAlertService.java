package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;
import java.util.Set;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;


public interface IAlertService {

	public List<String> getCommunityEmail(String city);

	public List<PersonInfoDto> getPersonInfo(String lastName);
	
	public List<ChildDto> getChildDto(String address);
	
	public List<String> getPhoneAlert(String address);
	
	public Set<String> getPersonsPhoneNumberByStation(String station) ;

}

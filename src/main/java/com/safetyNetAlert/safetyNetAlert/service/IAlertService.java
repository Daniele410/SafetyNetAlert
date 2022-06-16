package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.dto.ChildDto;
import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;
import com.safetyNetAlert.safetyNetAlert.dto.PhoneDto;

public interface IAlertService {

	public List<String> getCommunityEmail(String city);

	public List<PersonInfoDto> getPersonInfo(String lastName);
	
	public List<ChildDto> getChildDto(String address);
	
	public List<PhoneDto> getPhoneAlert(String phone);

}

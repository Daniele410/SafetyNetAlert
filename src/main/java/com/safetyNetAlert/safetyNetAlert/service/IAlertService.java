package com.safetyNetAlert.safetyNetAlert.service;

import java.util.List;

import com.safetyNetAlert.safetyNetAlert.dto.PersonInfoDto;

public interface IAlertService {

	public List<String> getCommunityEmail(String city);

	public List<PersonInfoDto> getPersonInfo(String firstName, String lastName);

}

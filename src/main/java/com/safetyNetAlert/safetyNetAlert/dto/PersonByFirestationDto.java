package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

public class PersonByFirestationDto {

	List<LightPersonDto> personByFirestationDto;
	Integer nbOfAdult;
	Integer nbOfChildren;
	
	
	public PersonByFirestationDto(List<LightPersonDto> personByFirestationDto, Integer nbOfAdult,
			Integer nbOfChildren) {
		
		this.personByFirestationDto = personByFirestationDto;
		this.nbOfAdult = nbOfAdult;
		this.nbOfChildren = nbOfChildren;
	}
	public List<LightPersonDto> getPersonByFirestationDto() {
		return personByFirestationDto;
	}
	public void setPersonByFirestationDto(List<LightPersonDto> personByFirestationDto) {
		this.personByFirestationDto = personByFirestationDto;
	}
	public Integer getNbOfAdult() {
		return nbOfAdult;
	}
	public void setNbOfAdult(Integer nbOfAdult) {
		this.nbOfAdult = nbOfAdult;
	}
	public Integer getNbOfChildren() {
		return nbOfChildren;
	}
	public void setNbOfChildren(Integer nbOfChildren) {
		this.nbOfChildren = nbOfChildren;
	}
	
	
	
	
	
	
	
	
	
}

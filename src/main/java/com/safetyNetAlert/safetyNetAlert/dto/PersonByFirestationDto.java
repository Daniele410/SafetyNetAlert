package com.safetyNetAlert.safetyNetAlert.dto;

import java.util.List;

public class PersonByFirestationDto {

	List<PersonByFirestationDto> personByFirestationDto;
	Integer nbOfAdult;
	Integer nbOfChildren;
	
	
	public PersonByFirestationDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PersonByFirestationDto(List<PersonByFirestationDto> personByFirestationDto, Integer nbOfAdult,
			Integer nbOfChildren) {
		super();
		this.personByFirestationDto = personByFirestationDto;
		this.nbOfAdult = nbOfAdult;
		this.nbOfChildren = nbOfChildren;
	}
	
	
	


	public List<PersonByFirestationDto> getPersonByFirestationDto() {
		return personByFirestationDto;
	}
	public void setPersonByFirestationDto(List<PersonByFirestationDto> personByFirestationDto) {
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

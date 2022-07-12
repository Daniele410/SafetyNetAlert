package com.safetyNetAlert.safetyNetAlert.model;

public class Firestation
 {
	private String address;
	private String station;

	public Firestation() {

	}

	public Firestation(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}


	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}

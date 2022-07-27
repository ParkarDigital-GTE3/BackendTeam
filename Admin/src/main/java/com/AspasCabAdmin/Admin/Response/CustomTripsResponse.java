package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

public class CustomTripsResponse {

	private Date date;
	private String status;
	private List<String> trips;
	public CustomTripsResponse() {
		// TODO Auto-generated constructor stub
	}
	public CustomTripsResponse(Date date, String status, List<String> trips) {
		this.date = date;
		this.status = status;
		this.trips = trips;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getTrips() {
		return trips;
	}
	public void setTrips(List<String> trips) {
		this.trips = trips;
	}
	
}

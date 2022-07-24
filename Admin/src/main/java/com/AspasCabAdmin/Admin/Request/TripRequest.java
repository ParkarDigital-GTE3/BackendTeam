package com.AspasCabAdmin.Admin.Request;

public class TripRequest {

	private String email;
	private long id;
	private String date;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public TripRequest(String email, long id, String date) {
		this.email = email;
		this.id = id;
		this.date = date;
	}
	public TripRequest() {
		// TODO Auto-generated constructor stub
	}
	
}

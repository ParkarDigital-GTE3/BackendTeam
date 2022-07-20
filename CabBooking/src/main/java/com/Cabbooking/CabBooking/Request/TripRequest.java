package com.Cabbooking.CabBooking.Request;

public class TripRequest {

	private String email;
	private long id;
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
	public TripRequest(String email, long id) {
		this.email = email;
		this.id = id;
	}
	public TripRequest() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TripRequest [email=" + email + ", id=" + id + "]";
	}
	
}

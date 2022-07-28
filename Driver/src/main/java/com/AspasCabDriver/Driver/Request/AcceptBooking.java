package com.AspasCabDriver.Driver.Request;

public class AcceptBooking {

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
	public AcceptBooking(String email, long id) {
		super();
		this.email = email;
		this.id = id;
	}
	
}

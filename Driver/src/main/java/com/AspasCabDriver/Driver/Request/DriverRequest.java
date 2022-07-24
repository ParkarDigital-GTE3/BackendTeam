package com.AspasCabDriver.Driver.Request;

public class DriverRequest {
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
	public DriverRequest(String email, long id) {
		this.email = email;
		this.id = id;
	}
	public DriverRequest() {
		// TODO Auto-generated constructor stub
	}
	

}

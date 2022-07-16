package com.Cabbooking.CabBooking.Request;

public class UpdateUserRequest {
	
	 private String contactNumber;
	 private String name; 
	 private String password;
	 private String email;
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UpdateUserRequest(String contactNumber, String name, String password, String email) {
		this.contactNumber = contactNumber;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	 
	
}

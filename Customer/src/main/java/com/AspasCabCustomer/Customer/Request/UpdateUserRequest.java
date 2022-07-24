package com.AspasCabCustomer.Customer.Request;

public class UpdateUserRequest {
	
	 private String contactNumber;
	 private String name; 
	 private String email;
	 private String dateOfBirth;
	 private String password;
	
	public UpdateUserRequest(String contactNumber, String name, String email, String dateOfBirth, String password) {
		this.contactNumber = contactNumber;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UpdateUserRequest() {
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
	
}

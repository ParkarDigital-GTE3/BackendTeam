package com.AspasCabCustomer.Customer.Request;

public class CustomerRequest {
	private String email;
	private String password;
	private long id;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CustomerRequest() {
		// TODO Auto-generated constructor stub
	}
	public CustomerRequest(String email, String password, long id) {
		this.email = email;
		this.password = password;
		this.id = id;
	}
	

}

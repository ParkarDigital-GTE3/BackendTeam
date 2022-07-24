package com.AspasCabCustomer.Customer.Response;

import java.util.Date;

public class CustomerResponseForInvalidLogin {
	
	 private String message;
	 private Date timestamp;
	public CustomerResponseForInvalidLogin(String message, Date timestamp) {
		this.message = message;
		this.timestamp = timestamp;
	}
	public CustomerResponseForInvalidLogin() {
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	 


}

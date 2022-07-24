package com.AspasCabCustomer.Customer.Response;

import java.util.Date;

public class CustomResponseForLogin {
	private Date timestamp;
    private String message;
    private String status;
    private String jwt;
    
    private String username;
    
	public CustomResponseForLogin(Date timestamp, String message, String status, String jwt, String username) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.jwt = jwt;
		this.username = username;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public CustomResponseForLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}

package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class EmailRequest {
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmailRequest() {
		// TODO Auto-generated constructor stub
	}

	public EmailRequest(String email) {
		this.email = email;
	}
	

}

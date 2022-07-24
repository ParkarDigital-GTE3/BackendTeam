package com.AspasCabCustomer.Customer.Request;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class EmailOtpRequest {
	private int otp;
	private String email;
	private String role;
	
	

	public EmailOtpRequest(int otp, String email, String role) {
		this.otp = otp;
		this.email = email;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public EmailOtpRequest() {
		// TODO Auto-generated constructor stub
	}


	

}

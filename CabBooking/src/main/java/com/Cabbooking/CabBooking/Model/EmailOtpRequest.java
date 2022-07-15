package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class EmailOtpRequest {
	private int otp;
	private String email;

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

	public EmailOtpRequest(int otp, String email) {
		this.otp = otp;
		this.email = email;
	}
	
	

}

package com.Cabbooking.CabBooking.Model;

import org.springframework.stereotype.Component;

@Component
public class OtpRequest {

	private int otp;

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public OtpRequest() {
		// TODO Auto-generated constructor stub
	}

	public OtpRequest(int otp) {
		this.otp = otp;
	}

	
	
}


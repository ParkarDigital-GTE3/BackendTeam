package com.Cabbooking.CabBooking.Service;

public interface EmailVerificationService {
	
	public boolean sendEmail(String subject,String message,String to);

}

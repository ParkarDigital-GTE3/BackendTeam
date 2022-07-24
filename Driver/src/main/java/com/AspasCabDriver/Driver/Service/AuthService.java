package com.AspasCabDriver.Driver.Service;


import com.AspasCabDriver.Driver.Model.CabDriver;
import com.AspasCabDriver.Driver.Model.Customer;
import com.mysql.cj.jdbc.Driver;


public interface AuthService {
	// User createUser(User userDetails);
	
	public boolean sendEmail(String subject,String message,String to);

	 
	CabDriver createDriver(CabDriver driver);

	CabDriver fetchDriverByEmail(String emailId);

 
	// User fetchUserByEmail(String emailId);

	//void updatePassword(String emailId, String newPassword);


	void updateDriverPassword(String emailId, String newPassword);

	CabDriver fetchDriverById(long driver_id);


	public Customer fetchCustomerByEmail(String customerEmail);


	public CabDriver fetchDriverByContactNumber(String contactNumber);



}

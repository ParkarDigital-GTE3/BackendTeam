package com.AspasCabCustomer.Customer.Service;

import com.AspasCabCustomer.Customer.Model.Customer;
import com.mysql.cj.jdbc.Driver;


public interface AuthService {
	// User createUser(User userDetails);
	
	public boolean sendEmail(String subject,String message,String to);
	

	Customer createCustomer(Customer customer);
	 
	 
	Customer fetchCustomerByEmail(String emailId);

	//void updatePassword(String emailId, String newPassword);

	
	void updateCustomerPassword(String emailId, String newPassword);


	public Customer fetchCustomerByContactNo(String contactNo);




}

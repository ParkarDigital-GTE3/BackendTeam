package com.Cabbooking.CabBooking.Service;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.User;
import com.mysql.cj.jdbc.Driver;


public interface AuthService {
	// User createUser(User userDetails);
	 
	Customer createCustomer(Customer customer);
	 
	CabDriver createDriver(CabDriver driver);
	 
	CabDetails createCab(CabDetails cabDetails);
	
	
	 
	Customer fetchCustomerByEmail(String emailId);

	CabDriver fetchDriverByEmail(String emailId);
	 
	CabDetails fetchCabByRegistrationNo(String cabRegistrationNo);
	
 
	// User fetchUserByEmail(String emailId);

	//void updatePassword(String emailId, String newPassword);

	
	void updateCustomerPassword(String emailId, String newPassword);

	void updateDriverPassword(String emailId, String newPassword);

	CabDriver fetchDriverById(long driver_id);

	CabDriver updateDriverCabId(CabDriver driver);

	CabDetails fetchCabById(long cab_id);

	//CabDetails createCab(CabDetails cabDetails, CabDriver cabDriver);

}

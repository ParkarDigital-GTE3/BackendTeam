package com.Cabbooking.CabBooking.Service;

import com.Cabbooking.CabBooking.Model.User;


public interface AuthService {
	 User createUser(User userDetails);

	 User fetchUserByEmail(String emailId);

	//void updatePassword(String emailId, String password);
}

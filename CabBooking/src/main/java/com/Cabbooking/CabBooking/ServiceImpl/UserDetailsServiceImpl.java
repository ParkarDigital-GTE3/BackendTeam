package com.Cabbooking.CabBooking.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Repository.UserRepository;
import com.Cabbooking.CabBooking.Security.UserDetailsImpl;


@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer customer = customerRepository.fetchCustomerByEmail(email);
        if(customer == null)
        {
        	CabDriver driver = driverRepository.fetchDriverByEmail(email);
        	if(driver == null) {
            throw new UsernameNotFoundException("Customer with this email not found");
        	}
        	return UserDetailsImpl.buildDriver(driver);
        }
        return UserDetailsImpl.build(customer);
	}

	
}

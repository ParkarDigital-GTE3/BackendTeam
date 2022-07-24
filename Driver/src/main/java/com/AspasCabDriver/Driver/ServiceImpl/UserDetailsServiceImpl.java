package com.AspasCabDriver.Driver.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AspasCabDriver.Driver.Model.CabDriver;
import com.AspasCabDriver.Driver.Repository.DriverRepository;
import com.AspasCabDriver.Driver.Security.UserDetailsImpl;



@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
        	CabDriver driver = driverRepository.fetchDriverByEmail(email);
        	if(driver == null) {
        		
            throw new UsernameNotFoundException("Driver with this email not found");
        	}
        	
        	return UserDetailsImpl.build(driver);

	}

	
}

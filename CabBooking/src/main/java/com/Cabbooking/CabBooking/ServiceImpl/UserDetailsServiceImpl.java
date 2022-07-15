package com.Cabbooking.CabBooking.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.UserRepository;
import com.Cabbooking.CabBooking.Security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.fetchUserByEmail(email);
        if(user == null)
        {
            throw new UsernameNotFoundException("Customer with this email not found");
        }
        return UserDetailsImpl.build(user);
	}

	
}

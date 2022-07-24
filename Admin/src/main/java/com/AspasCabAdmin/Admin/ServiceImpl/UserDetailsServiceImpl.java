package com.AspasCabAdmin.Admin.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AspasCabAdmin.Admin.Model.Admin;
import com.AspasCabAdmin.Admin.Repository.AdminRepository;
import com.AspasCabAdmin.Admin.Security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private AdminRepository adminRepository;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Admin admin = adminRepository.getAdminByEmail(email);
        if(admin == null)
        {
        
            throw new UsernameNotFoundException("Customer with this email not found");

        }
        return UserDetailsImpl.build(admin);
	}

	
}

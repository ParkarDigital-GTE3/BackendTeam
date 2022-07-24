package com.AspasCabAdmin.Admin.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AspasCabAdmin.Admin.Model.Admin;
import com.AspasCabAdmin.Admin.Repository.AdminRepository;
import com.AspasCabAdmin.Admin.Service.AdminAuthService;


@Service
public class AuthServiceImpl implements AdminAuthService {

    
  
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder encoder;
	
	// Email validation
	@Override
	public boolean emailValidation(String email)
	    {
	        
	        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
	                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	        if (email!=null)
	        {
	            if(email.matches(regexPattern))
	            {
	                return true;
	            }
	        }
	        return false;
	    }
    

	//Fetching the details
	
	//fetching 
    @Override
    public Admin fetchAdminByEmail(String emailId) {
        // TODO Auto-generated method stub
        return adminRepository.getAdminByEmail(emailId);
    }


	@Override
	public Admin createAdmin(Admin adminDetails) {
		adminDetails.setPassword(encoder.encode(adminDetails.getPassword()));
		return adminRepository.save(adminDetails);
	}

  





}

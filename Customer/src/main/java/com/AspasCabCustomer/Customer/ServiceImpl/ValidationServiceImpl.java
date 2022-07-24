package com.AspasCabCustomer.Customer.ServiceImpl;


import org.springframework.stereotype.Service;

import com.AspasCabCustomer.Customer.Service.ValidationService;

import java.util.regex.Pattern;


@Service
public class ValidationServiceImpl implements ValidationService
{

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
    
}

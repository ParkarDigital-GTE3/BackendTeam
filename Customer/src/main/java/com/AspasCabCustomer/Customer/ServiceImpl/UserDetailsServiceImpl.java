package com.AspasCabCustomer.Customer.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AspasCabCustomer.Customer.Model.Customer;
import com.AspasCabCustomer.Customer.Repository.CustomerRepository;
import com.AspasCabCustomer.Customer.Security.UserDetailsImpl;



@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer customer = customerRepository.fetchCustomerByEmail(email);
        if(customer == null)
        {
        	
            throw new UsernameNotFoundException("Customer with this email not found");
        }
        return UserDetailsImpl.build(customer);
	}

	
}

package com.AspasCabCustomer.Customer.Controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AspasCabCustomer.Customer.Model.Customer;
import com.AspasCabCustomer.Customer.Repository.CustomerRepository;
import com.AspasCabCustomer.Customer.Response.CustomResponseForLogin;
import com.AspasCabCustomer.Customer.Response.CustomResponseForNoUser;
import com.AspasCabCustomer.Customer.Security.JwtUtils;
import com.AspasCabCustomer.Customer.Security.UserDetailsImpl;
import com.AspasCabCustomer.Customer.Service.AuthService;




@RequestMapping("/login")
@RestController
@CrossOrigin
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
//	@Autowired
//    UserRepository userRepository;
	
	@Autowired
	CustomerRepository customerRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
	
    //Customer Login
	@PostMapping("/customerLogin")
	public ResponseEntity<Object> customerLogin(@RequestBody Customer customerDetails){
		
		Customer fetchCustomer = authService.fetchCustomerByEmail(customerDetails.getEmail());
	        if(fetchCustomer!=null)
	        {
	            if(encoder.matches(customerDetails.getPassword(), fetchCustomer.getPassword()))
	            {
	                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerDetails.getEmail(),customerDetails.getPassword()));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                String jwt = jwtUtils.generateJwtToken(authentication);

	                UserDetailsImpl details =(UserDetailsImpl)authentication.getPrincipal();
	                if(details != null)
	                {
	                    System.out.println(customerDetails.getPassword());
	                    CustomResponseForLogin response = new CustomResponseForLogin(new Date(),"Login Successfully","200",jwt, fetchCustomer.getEmail());
	                    return new ResponseEntity<Object>(response, HttpStatus.OK);
	                }
	                else {
	                    CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authenticaion","401");
	                    return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);

	                }

	            }else{
	                CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Invalid Credentials","401");
	                return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
	            }
	        }
	        else
	        {
	            CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"User Not Found","401");
	            return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
	        }
	    }
	
}
	


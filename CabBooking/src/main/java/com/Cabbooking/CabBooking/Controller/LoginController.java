package com.Cabbooking.CabBooking.Controller;

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

import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;

import com.Cabbooking.CabBooking.Response.CustomResponseForLogin;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Security.AuthEntryPointJwt;
import com.Cabbooking.CabBooking.Security.UserDetailsImpl;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.mysql.cj.jdbc.Driver;


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
	DriverRepository driverRepository;

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
	
	//Cab Driver Login
	@PostMapping("/driverLogin")
	public ResponseEntity<Object> driverLogin(@RequestBody CabDriver cabDriverDetails){
		
		CabDriver fetchDriver = authService.fetchDriverByEmail(cabDriverDetails.getEmail());
	        if(fetchDriver!=null)
	        {
	            if(encoder.matches(cabDriverDetails.getPassword(), fetchDriver.getPassword()))
	            {
	                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cabDriverDetails.getEmail(),cabDriverDetails.getPassword()));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                String jwt = jwtUtils.generateJwtToken(authentication);

	                UserDetailsImpl details =(UserDetailsImpl)authentication.getPrincipal();
	                if(details != null)
	                {
	                    System.out.println(cabDriverDetails.getPassword());
	                    CustomResponseForLogin response = new CustomResponseForLogin(new Date(),"Login Successfully","200",jwt, fetchDriver.getEmail());
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
	


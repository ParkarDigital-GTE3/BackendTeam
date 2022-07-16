package com.Cabbooking.CabBooking.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Service.AuthService;

@RequestMapping("/userLogin")
@RestController
public class SetUserProfileController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@PostMapping("/updateCustomerDetails")
	public ResponseEntity<Object> updateCustomerDetails(@RequestBody UpdateUserRequest customer)
		{
			Customer fetchCustomer = authService.fetchCustomerByEmail(customer.getEmail());

			if(!encoder.matches(customer.getPassword(), fetchCustomer.getPassword())) {
				CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Wrong password", new Date());
				return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
			}
			
			if(customer.getContactNumber()!="")
			{
				fetchCustomer.setContactNo(customer.getContactNumber());
			}

			customerRepository.save(fetchCustomer);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}

	
	@PostMapping("/updateDriverDetails")
	public ResponseEntity<Object> updateDriverDetails(@RequestBody UpdateUserRequest driver)
		{
			CabDriver fetchDriver = authService.fetchDriverByEmail(driver.getEmail());

			if(!encoder.matches(driver.getPassword(), fetchDriver.getPassword())) {
				CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Wrong password", new Date());
				return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
			}
			
			if(driver.getContactNumber()!="")
			{
				fetchDriver.setContactNumber(driver.getContactNumber());
			}
			
			driverRepository.save(fetchDriver);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}


}

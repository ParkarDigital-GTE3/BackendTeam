package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Repository.BookingRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.BookingResponse;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.BookingCabService;
import com.Cabbooking.CabBooking.Service.ValidationService;


@RestController
@RequestMapping("/login/Customer")
public class CustomerController {

	@Autowired
	EmailOtpRequest emailOtpRequest;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

	@Autowired
	AuthService authService;

	@Autowired
	ValidationService validationService;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	BookingCabService bookingCabService;
	
	@Autowired
	BookingRepository bookingRepository;
	
	
	
    // Fetch Customer By Email
	@GetMapping("/getCustomer/{email}")
	public ResponseEntity<Object> getCustomer(@PathVariable("email") String email)
		{
			System.out.println(email);
			Customer fetchCustomer = authService.fetchCustomerByEmail(email);
			if (fetchCustomer == null)
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authentication","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
		
			return new ResponseEntity<Object>(fetchCustomer,HttpStatus.OK);
		}
	
	
	//Update Customer Password
	@PostMapping("/updateCustomerPassword")
	public String updateCustomerPassword(@RequestBody UpdatePasswordRequest Details)
		{
			String emailId = Details.getEmail();
			String oldPassword = Details.getOldPassword();
			String newPassword = Details.getNewPassword();

			Customer fetchCustomer = authService.fetchCustomerByEmail(emailId);
			if(fetchCustomer == null) {
				return "Customer Not Found";
			}

			if(!encoder.matches(oldPassword, fetchCustomer.getPassword()))
				return "Incorrect Current Password";

			authService.updateCustomerPassword(emailId, newPassword);
			return "Update Successful";


		}

	
	//Update Customer Profile
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
				
			if(customer.getName()!="")
			{
				fetchCustomer.setName(customer.getName());
			}
			if(customer.getEmail()!="")
			{
				fetchCustomer.setEmail(customer.getEmail());
			}


			customerRepository.save(fetchCustomer);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}

	
	// Create Booking Customer
	@PostMapping("/bookCab")
	public ResponseEntity<Object> bookCab(@RequestBody Booking bookingDetails){
			
			if(bookingDetails == null) {
			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"No Booking Details","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}
			Booking result = bookingCabService.bookCab(bookingDetails);
			BookingResponse response = new BookingResponse(new Date(),"Booking Confirmed","200",result);
			return new ResponseEntity<Object>(response,HttpStatus.CREATED);
		}
	
}

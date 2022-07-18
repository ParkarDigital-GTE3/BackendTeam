package com.Cabbooking.CabBooking.Controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;

import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Response.CustomerDetailsResponse;
import com.Cabbooking.CabBooking.Response.DriverDetailsResponse;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.EmailVerificationService;
import com.Cabbooking.CabBooking.Service.ValidationService;


@RestController
@RequestMapping("/register")
@CrossOrigin
public class AuthController
{
	
	@Autowired
	private EmailVerificationService emailVerificationService;
	
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
	
	
	// Email OTP Verification
	@PostMapping("/sendEmailOtp")
	public ResponseEntity<?> sendEmailOtp(@RequestBody EmailOtpRequest emailOtpRequest)
	{
		if(validationService.emailValidation(emailOtpRequest.getEmail())) {
			if((emailOtpRequest.getRole()).equals("Customer")) {
				Customer fetchCustomer = authService.fetchCustomerByEmail(emailOtpRequest.getEmail());
			if(fetchCustomer != null) {
				return ResponseEntity.ok("EmailId Already Exists!!!");
				
			}else {
			System.out.println("Email"+ emailOtpRequest.getEmail());
			System.out.println("OTP"+ emailOtpRequest.getOtp());
			
			String subject = "Email Verification From Cab Booking";
			String message = "OTP "+emailOtpRequest.getOtp();
			String to = emailOtpRequest.getEmail();
			
			boolean flag = this.emailVerificationService.sendEmail(subject, message, to);
			if(flag) {
				return ResponseEntity.ok("Email Sent!!!!!...");
			}
			}
			}
			else if((emailOtpRequest.getRole()).equals("Driver")) {
				CabDriver fetchDriver = authService.fetchDriverByEmail(emailOtpRequest.getEmail());
				if(fetchDriver != null) {
					return ResponseEntity.ok("EmailId Already Exists!!!");
					
				}else {
				System.out.println("Email"+ emailOtpRequest.getEmail());
				
				System.out.println("OTP"+ emailOtpRequest.getOtp());
				
				String subject = "Email Verification From Cab Booking";
				String message = "OTP "+emailOtpRequest.getOtp();
				String to = emailOtpRequest.getEmail();
				
				boolean flag = this.emailVerificationService.sendEmail(subject, message, to);
				if(flag) {
					return ResponseEntity.ok("Email Sent!!!!!...");
				}
				}
			}
		}
	return ResponseEntity.ok("Check your Email Id.. Email Not Sent!!!..");
	}

	//Create Customer
    @PostMapping("/registerCustomer")
	public ResponseEntity<Object> createUser(@RequestBody Customer customerDetails){
		Customer fetchCustomer = authService.fetchCustomerByEmail(customerDetails.getEmail());

		if(fetchCustomer == null) {
			if (validationService.emailValidation(customerDetails.getEmail())) {
				Customer customer= authService.createCustomer(customerDetails);

				CustomerDetailsResponse response = new CustomerDetailsResponse(new Date(), "Customer Created Succesfully", "200", customer);

				return new ResponseEntity<Object>(response, HttpStatus.CREATED);
			}
			else
			{
				UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}

		}else{

			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
    }
    
    //Create  Cab Driver
    @PostMapping("/registerDriver")
	public ResponseEntity<Object> createDriver(@RequestBody CabDriver driverDetials)
    	{
    	CabDriver fetchDriver = authService.fetchDriverByEmail(driverDetials.getEmail());

		if(fetchDriver == null) {
			if (validationService.emailValidation(driverDetials.getEmail())) {
				driverDetials.setActivationStatus("0");
//				driverDetials.setCab_id(null);
//				driverDetials.setLicenseNo(null);
				CabDriver driver = authService.createDriver(driverDetials);

				DriverDetailsResponse response = new DriverDetailsResponse(new Date(), "Driver Created Succesfully", "201", driver);

				return new ResponseEntity<Object>(response, HttpStatus.CREATED);
			}
			else
			{
				UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}

		}else {

			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);

		}
    }

    

}


	



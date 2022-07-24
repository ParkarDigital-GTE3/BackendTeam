package com.AspasCabCustomer.Customer.Controller;

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

import com.AspasCabCustomer.Customer.Model.Customer;
import com.AspasCabCustomer.Customer.Request.EmailOtpRequest;
import com.AspasCabCustomer.Customer.Response.CustomResponse;
import com.AspasCabCustomer.Customer.Response.CustomResponseForNoUser;
import com.AspasCabCustomer.Customer.Response.CustomerDetailsResponse;
import com.AspasCabCustomer.Customer.Security.JwtUtils;
import com.AspasCabCustomer.Customer.Service.AuthService;
import com.AspasCabCustomer.Customer.Service.ValidationService;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class AuthController
{
	
	@Autowired
	EmailOtpRequest emailOtpRequest;

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
			
			boolean flag = this.authService.sendEmail(subject, message, to);
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
				Customer fetchContact = authService.fetchCustomerByContactNo(customerDetails.getContactNo());
				if(fetchContact==null) {
					Customer customer= authService.createCustomer(customerDetails);
	
					CustomerDetailsResponse response = new CustomerDetailsResponse(new Date(), "Customer Created Succesfully", "200", customer);
	
					return new ResponseEntity<Object>(response, HttpStatus.CREATED);
			}else {
				CustomResponse response = new CustomResponse(new Date(),"Contact Number Already Exists","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}

		}else{

			CustomResponse response = new CustomResponse(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
    }


    

}


	



package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Repository.UserRepository;
import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerDetailsResponse;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.DriverDetailsResponse;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.EmailVerificationService;
import com.Cabbooking.CabBooking.Service.ValidationService;
import com.mysql.cj.jdbc.Driver;


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
	UserRepository userRepository;

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
	public ResponseEntity<?> sendEmailOtp(@RequestBody EmailOtpRequest emailOtpRequest,HttpSession session)
	{
		if(validationService.emailValidation(emailOtpRequest.getEmail())) {
			if(emailOtpRequest.getRole()=="Customer") {
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
			else if(emailOtpRequest.getRole()=="Driver") {
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

    
    // Fetch All Customer
 	@GetMapping("/getCustomers")
 	public ResponseEntity<Object> getCustomers()
 		{
 			List<Customer> fetchCustomer = customerRepository.findAll();
 			if (fetchCustomer == null)
 			{
 				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authentication","409");
 				return new ResponseEntity<Object>(response,HttpStatus.OK);
 			}
 		
 			return new ResponseEntity<Object>(fetchCustomer,HttpStatus.OK);
 		}
 	
    // Fetch  All Driver
 	@GetMapping("/getCabDrivers")
 	public ResponseEntity<Object> getCabDrivers()
 		{
 			List<CabDriver> fetchDriver = driverRepository.findAll();
 			if (fetchDriver == null)
 			{
 				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authentication","409");
 				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
 			}
 		
 			return new ResponseEntity<Object>(fetchDriver,HttpStatus.FOUND);
 		}
 	
 	
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
	
    // Fetch Driver By Email
	@GetMapping("/getDriver/{email}")
	public ResponseEntity<Object> getDriver(@PathVariable("email") String email)
		{
			System.out.println(email);
			CabDriver fetchDriver = authService.fetchDriverByEmail(email);
			if (fetchDriver == null)
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authentication","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
		
			return new ResponseEntity<Object>(fetchDriver,HttpStatus.OK);
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
	
	//Update Driver Password
	@PostMapping("/updateDriverPassword")
	public String updateDriverPassword(@RequestBody UpdatePasswordRequest Details)
		{
			String emailId = Details.getEmail();
			String oldPassword = Details.getOldPassword();
			String newPassword = Details.getNewPassword();

			CabDriver fetchDriver = authService.fetchDriverByEmail(emailId);

			if(!encoder.matches(oldPassword, fetchDriver.getPassword()))
				return "Incorrect Current Password";

			authService.updateDriverPassword(emailId, newPassword);
			return "Update Successful";


		}

	
   
}


	



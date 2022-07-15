package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.EmailOtpRequest;
import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.UserRepository;
import com.Cabbooking.CabBooking.Response.UserDetailsResponse;
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
	UserRepository userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
//
//	@Autowired
//	AccountService accountService;

	@Autowired
	AuthService authService;

	@Autowired
	ValidationService validationService;

	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/sendEmailOtp")
	public ResponseEntity<?> sendEmailOtp(@RequestBody EmailOtpRequest emailOtpRequest,HttpSession session) {
		if(validationService.emailValidationForVerification(emailOtpRequest.getEmail())) {
		User fetchuser = authService.fetchUserByEmail(emailOtpRequest.getEmail());
		if(fetchuser != null) {
			return ResponseEntity.ok("EmailId Already Exists!!!");
			
		}else {
		System.out.println("Email"+ emailOtpRequest.getEmail());
		//generate random 4 digit number
		
		System.out.println("OTP"+ emailOtpRequest.getOtp());
		
		String subject = "Email Verification From Cab Booking";
		String message = "OTP "+emailOtpRequest.getOtp();
		String to = emailOtpRequest.getEmail();
		
		boolean flag = this.emailVerificationService.sendEmail(subject, message, to);
		if(flag) {
			session.setAttribute("myotp", emailOtpRequest.getOtp());
			return ResponseEntity.ok("Email Sent!!!!!...");
			
		}
		else {
			session.setAttribute("message", "Check your Email Id");
			return ResponseEntity.ok("Check your Email Id.. Email Not Sent!!!..");
		}
		
		}
		}
		else {
			return ResponseEntity.ok("Invalid Email Id");
		}
	}
	
	


    @PostMapping("/registerUser")
	public ResponseEntity<Object> createUser(@RequestBody User userDetails){
    	if((userDetails.getRole()).equals("Customer")) {
		User fetchuser = authService.fetchUserByEmail(userDetails.getEmail());

		if(fetchuser == null) {
			if (validationService.emailValidation(userDetails)) {
				User user= authService.createUser(userDetails);

				UserDetailsResponse response = new UserDetailsResponse(new Date(), "User Created Succesfully", "200", user);

				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}

		}else{

			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
    	}else if((userDetails.getRole()).equals("Driver")){

		User fetchuser = authService.fetchUserByEmail(userDetails.getEmail());

		if(fetchuser == null) {
			if (validationService.emailValidation(userDetails)) {
				User user= authService.createUser(userDetails);

				UserDetailsResponse response = new UserDetailsResponse(new Date(), "User Created Succesfully", "200", user);

				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}

		}else {

			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);

		}
    }else {
    	UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Select Type of User","409");
		return new ResponseEntity<Object>(response,HttpStatus.OK);
    	
    }
	
}
    @GetMapping("/demo")
    public String demoFunction() {
		return "Need JWT";
	}
}		

    
    
//	@PostMapping("/getUserDetails")
//	public ResponseEntity<Object> getCustomer(@RequestBody User userDetails)
//	{
//		System.out.println(userDetails.getEmailId());
//		User fetchCustomer = authService.fetchUserByEmail(userDetails.getEmailId());
//		if (fetchCustomer == null)
//		{
//			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Error in authentication","409");
//			return new ResponseEntity<Object>(response,HttpStatus.OK);
//		}
//		Account account = accountService.fetchAccountByEmail(userDetails.getEmailId());
//		fetchCustomer.setPassword(null);
//		GetCustomerResponse response = new GetCustomerResponse(fetchCustomer,account.getBalance());
//		return new ResponseEntity<Object>(response,HttpStatus.OK);
//	}
//	
//	
//	
//	@PostMapping("/updatePassword") // Done by Tejesh and Vishwachand
//	public String updatePassword(@RequestBody UpdatePasswordRequest Details)
//	{
//		String emailId = Details.getEmailId();
//		String oldPassword = Details.getOldPassword();
//		String newPassword = Details.getNewPassword();
//
//		Customer fetchCustomer = authService.UserByEmail(emailId);
//
//		if(!encoder.matches(oldPassword, fetchCustomer.getPassword()))
//			return "Incorrect Current Password";
//
//		authService.updatePassword(emailId, newPassword);
//		return "Update Successful";
//
//
//	}
//
//	@PostMapping("/updateUser")
//	public ResponseEntity<Object> updateUser(@RequestBody UpdateUserRequest user)
//	{
//		User fetchCustomer = authService.fetchCustomerByEmail(user.getEmailId());
//
//		if(!encoder.matches(user.getPassword(), fetchCustomer.getPassword())) {
//			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Wrong password", new Date());
//			return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
//		}
//		if(user.getAddress()!="")
//		{
//			fetchCustomer.setAddress(user.getAddress());
//		}
//		if(user.getName()!="")
//		{
//			fetchCustomer.setName(user.getName());
//		}
//		if(user.getPhoneNumber()!="")
//		{
//			fetchCustomer.setPhoneNumber(user.getPhoneNumber());
//		}
//		if(user.getEmailId()!="")
//		{
//			fetchCustomer.setEmailId(user.getEmailId());
//		}
//		custoers.save(fetchCustomer);
//		CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
//		return new ResponseEntity<Object>(response,HttpStatus.OK);
//	}


	



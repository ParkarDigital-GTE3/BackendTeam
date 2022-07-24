package com.AspasCabDriver.Driver.Controller;

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

import com.AspasCabDriver.Driver.Model.CabDriver;
import com.AspasCabDriver.Driver.Request.EmailOtpRequest;
import com.AspasCabDriver.Driver.Response.CustomResponse;
import com.AspasCabDriver.Driver.Response.DriverDetailsResponse;
import com.AspasCabDriver.Driver.Security.JwtUtils;
import com.AspasCabDriver.Driver.Service.AuthService;
import com.AspasCabDriver.Driver.Service.ValidationService;



@RestController
@RequestMapping("/register")
@CrossOrigin
public class AuthController
{
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	EmailOtpRequest emailOtpRequest;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;


	@Autowired
	AuthService authService;


	@Autowired
	PasswordEncoder encoder;
	
	
	// Email OTP Verification
	@PostMapping("/sendEmailOtp")
	public ResponseEntity<?> sendEmailOtp(@RequestBody EmailOtpRequest emailOtpRequest)
	{
		if(validationService.emailValidation(emailOtpRequest.getEmail())) {
			 if((emailOtpRequest.getRole()).equals("Driver")) {
				CabDriver fetchDriver = authService.fetchDriverByEmail(emailOtpRequest.getEmail());
				if(fetchDriver != null) {
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

	
    //Create  Cab Driver
    @PostMapping("/registerDriver")
	public ResponseEntity<Object> createDriver(@RequestBody CabDriver driverDetials)
    	{
    	CabDriver fetchDriver = authService.fetchDriverByEmail(driverDetials.getEmail());

		if(fetchDriver == null) {
			if (validationService.emailValidation(driverDetials.getEmail())) {
				CabDriver driverNo = authService.fetchDriverByContactNumber(driverDetials.getContactNumber());
				if(driverNo == null) {
				driverDetials.setActivationStatus("0");
//				driverDetials.setCab_id(null);
//				driverDetials.setLicenseNo(null);
				CabDriver driver = authService.createDriver(driverDetials);

				DriverDetailsResponse response = new DriverDetailsResponse(new Date(), "Driver Created Succesfully", "200", driver);

				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}else
			{
				CustomResponse response = new CustomResponse(new Date(),"Contact Number Already Exists!","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}

		}else {

			CustomResponse response = new CustomResponse(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);

		}
    }

    

}


	



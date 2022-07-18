package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Repository.BookingRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.BookingCabService;
import com.Cabbooking.CabBooking.Service.ValidationService;


@RestController
@RequestMapping("/login/Driver")
public class DriverController {
	@Autowired
	EmailOtpRequest emailOtpRequest;
	
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


	//Update Driver Profile
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
			if(driver.getName()!="")
			{
				fetchDriver.setName(driver.getName());
			}
			if(driver.getEmail()!="")
			{
				fetchDriver.setEmail(driver.getEmail());
			}
			
			driverRepository.save(fetchDriver);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	//Get Bookings Driver
	@GetMapping("/getBookings")
	public ResponseEntity<Object> getBookings(){
		List<Booking> response = bookingRepository.findAll();
		return new ResponseEntity<Object>(response,HttpStatus.FOUND);
	}
	
	
	//get single Booking by id
	@GetMapping("/getBooking/{id}")
	public ResponseEntity<Object> getBookings(@PathVariable("id") long id){
		Optional<Booking> response = bookingRepository.findById(id);
		return new ResponseEntity<Object>(response,HttpStatus.FOUND);
	}
	
	
	//update booking status as closed
	@PatchMapping("/acceptBooking/{id}")
	public ResponseEntity<Object> updateBookingStatus(@PathVariable("id") long id){
		Booking fetchBooking = bookingRepository.getById(id);
		
		if(fetchBooking==null) {
			UserResponseForNoUser response =  new UserResponseForNoUser(new Date(),"Booking does not Exists!!","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
		Booking response = bookingCabService.updateBooking(id);
		return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
	}
	
	
}

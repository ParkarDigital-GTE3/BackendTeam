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
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.TripDetails;
import com.Cabbooking.CabBooking.Repository.BookingRepository;
import com.Cabbooking.CabBooking.Repository.CarTypesAndRatesRepo;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Repository.LocationRepository;
import com.Cabbooking.CabBooking.Repository.TripRepository;
import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Request.TripRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.BookingCabService;
import com.Cabbooking.CabBooking.Service.TripService;
import com.Cabbooking.CabBooking.Service.ValidationService;


@RestController
@RequestMapping("/login/Driver")
public class DriverController {
	@Autowired
	EmailOtpRequest emailOtpRequest;
	
	@Autowired
	DriverRepository driverRepository;

	@Autowired
	CustomerRepository customerRepository;
	
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
	
	@Autowired
	LocationRepository  locationRepository;
	
	@Autowired
	TripRepository tripRepository;
	
	@Autowired
	CarTypesAndRatesRepo carTypesAndRatesRepo;
	
	@Autowired
	TripService tripService;

 	

    // Fetch Driver By Email// View profile
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


	//Update Driver Profile Personal Account
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
			if(driver.getDateOfBirth()!="")
			{
				fetchDriver.setDateOfBirth(driver.getDateOfBirth());
			}
			
			driverRepository.save(fetchDriver);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	//Get Bookings Driver // View Booking Request
	@GetMapping("/getBookings")
	public ResponseEntity<Object> getBookings(){
		List<Booking> response = bookingRepository.findBookingByStatus();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	//get single Booking by id
	@GetMapping("/getBooking/{id}")
	public ResponseEntity<Object> getBookings(@PathVariable("id") long id){
		Optional<Booking> response = bookingRepository.findById(id);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	//update booking status as closed //Accept Booking By Driver
	@PostMapping("/acceptBooking/{id}")
	public ResponseEntity<Object> updateBookingStatus(@PathVariable("id") long id,@RequestBody UpdateUserRequest email){
		Booking fetchBooking = bookingRepository.getById(id);
		
		if(fetchBooking==null) {
			UserResponseForNoUser response =  new UserResponseForNoUser(new Date(),"Booking does not Exists!!","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
		CabDriver driver = authService.fetchDriverByEmail(email.getEmail());
		long driver_id = driver.getDriver_id();
		fetchBooking.setDriverId(driver_id);
		Booking response = bookingCabService.updateBooking(id);
		
		return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
	}

/*
		//Start Trip
		@PatchMapping("/startTrip/{id}")
		public ResponseEntity<Object> startTrip(@PathVariable("id")long id){
			Booking fetchBooking = bookingRepository.getById(id);
			CabDriver driver = driverRepository.getById(fetchBooking.getDriverId());
			Customer customer = customerRepository.getById(fetchBooking.getCustomerId());
			TripDetails trip = tripRepository.saveAll(fetchBooking.getSource(),fetchBooking.getDestination(),driver.getEmail(),customer.getEmail());
			trip.setCustomerName(customer.getName());
			trip.setDriverName(driver.getName());
			trip.setCustomerContactNo(customer.getContactNo());
			trip.setDriverContactNo(driver.getContactNumber());
			trip.setCabCapacity(fetchBooking.getCabCapacity());
			trip.setCabType(fetchBooking.getCabType());
			long ratesKm = carTypesAndRatesRepo.fetchRates(fetchBooking.getCabType(),fetchBooking.getCabCapacity());
			trip.setRatesPerKm(ratesKm);
			long totalDist = locationRepository.fetchTotalDistance(fetchBooking.getSource(),fetchBooking.getDestination());
			
			Date date = new Date();
			int tripDate =date.getDate();
			trip.setDate(tripDate);
			Long tripTime =date.getTime();
			
			trip.setTime(tripTime);
			trip.setTotalFare((ratesKm)*(totalDist));
			
			TripDetails response = tripService.updateTrip(trip);
			return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
		}
	*/
		
/*
	// Complete Trip Add details to trip table
	@GetMapping("/CompleteTrip/{email}")
	public ResponseEntity<Object> completeTrip(@PathVariable("email") String email){
		//Driver Name and contact No JWT token
		//Cab Registration Number using Driver Details
		
		TripDetails trip = tripRepository.getTripByEmail(email);
		return new ResponseEntity<Object>(trip,HttpStatus.OK);
		
	}
	
*/
	// View Specific Trip
	 @PostMapping("/viewTrip/{id}")
	 public ResponseEntity<Object> tripHistorySpecific(@PathVariable("id") long id){
		 TripDetails trip = tripRepository.getById(id);
		 return new ResponseEntity<Object>(trip,HttpStatus.OK);
	 }

/*	
	// View Trip History Driver
	@GetMapping("/viewTripHistory/{email}")
	public  ResponseEntity<Object> TripHistoryDriver(@PathVariable("email") String email){

		List<TripDetails> response = tripRepository.fetchTripByDriverEmail(email);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	

	// View Earning
	@GetMapping("/total/{email}")
	public  Long findByemail(@PathVariable("email") String email)
	{
		return tripRepository.totalEarningByName(email);
	}
	*/
}

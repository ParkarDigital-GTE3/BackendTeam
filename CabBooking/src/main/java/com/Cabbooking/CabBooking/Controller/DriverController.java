package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.Cabbooking.CabBooking.Request.DriverRequest;
import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Request.TripRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AdminService;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.BookingCabService;
import com.Cabbooking.CabBooking.Service.CustomerService;
import com.Cabbooking.CabBooking.Service.DriverService;
import com.Cabbooking.CabBooking.Service.TripService;


@RestController
@RequestMapping("/login/driver")
@CrossOrigin
public class DriverController {
	private static final Logger log = LoggerFactory.getLogger(DriverController.class);
	@Autowired
	DriverService driverService;

	@Autowired
	CustomerService customerService;

	@Autowired
	AdminService adminService;
	
	@Autowired
	AuthService authService;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	BookingCabService bookingCabService;

	@Autowired
	TripService tripService;

 	

    // Fetch Driver By Email// View profile
	@PostMapping("/getDriver")
	public ResponseEntity<Object> getDriver(@RequestBody UpdateUserRequest getDriverRequest)
		{
			CabDriver fetchDriver = authService.fetchDriverByEmail(getDriverRequest.getEmail());
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
			
			CabDriver response = driverService.updateDriver(fetchDriver);
			
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	//Get Bookings Driver // View Booking Request
	@GetMapping("/getBookings")
	public ResponseEntity<Object> getBookings(){
		List<Booking> response = bookingCabService.findBookingByStatus();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	//get single Booking by id
	@PostMapping("/getBooking")
	public ResponseEntity<Object> getBookingsById(@RequestBody Booking bookingRequest){
		Optional<Booking> response = bookingCabService.findBookingById(bookingRequest.getBooking_id());
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	//update booking status as closed //Accept Booking By Driver
	@PostMapping("/acceptBooking/{booking_id}")
	public ResponseEntity<Object> updateBookingStatus(@PathVariable("booking_id") long id,@RequestBody UpdateUserRequest email){
		Booking fetchBooking = bookingCabService.getBookingById(id);
		
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

	

	//Start Trip, Add details to trip
	@PostMapping("/CompleteTrip")
	public ResponseEntity<Object> startTrip(@RequestBody TripDetails trip){
			log.info("**************In Start Trip*********");
			Booking fetchBooking = bookingCabService.getBookingById(trip.getBooking_id());
			log.info("after fetchbooking"+fetchBooking);
			System.out.println(trip.getDriverEmail());
			CabDriver driver = driverService.fetchDriverByEmail(trip.getDriverEmail());
			log.info("After driver fetch"+driver);
			System.out.println(fetchBooking.getCustomerEmail());
			Customer customer = customerService.fetchCustomerByEmail(fetchBooking.getCustomerEmail());
			log.info("After customer fetch"+customer);
			trip.setCustomerEmail(customer.getEmail());
			trip.setCustomerName(customer.getName());
			log.info("After customerName fetch"+customer.getName());
			trip.setDriverName(driver.getName());
			log.info("After DriverName fetch"+driver.getName());
			
			
			trip.setSource(fetchBooking.getSource());
			log.info("After source fetch"+fetchBooking.getSource());
			trip.setDestination(fetchBooking.getDestination());
			log.info("After destination fetch"+fetchBooking.getDestination());
			
			
			trip.setCabType(driver.getCab_id().getCabType());
			log.info("After cab Type fetch"+driver.getCab_id().getCabType());
			trip.setCabCapacity(driver.getCab_id().getCabCapacity());
			log.info("After cab Capacity fetch"+driver.getCab_id().getCabCapacity());
			trip.setCabRegistrationNo(driver.getCab_id().getCabRegistrationNo());
			log.info("After Cab Registration fetch"+driver.getCab_id().getCabRegistrationNo());
			
			
			
			long totalDist = adminService.fetchTotalDistanceByLocations(fetchBooking.getSource(),fetchBooking.getDestination());
			trip.setTotalDistance(totalDist);
			log.info("After Set Total Distance fetch"+totalDist);
			String cabType = fetchBooking.getCabType();
			log.info("After cabType fetch"+cabType);
			String cabCap = fetchBooking.getCabCapacity();
			log.info("After cabCap fetch"+cabCap);
			long ratesKm = adminService.fetchRatesPerKmByCab(fetchBooking.getCabType(),fetchBooking.getCabCapacity());
			log.info("After ratesKm fetch"+ratesKm);
			trip.setRatesPerKm(ratesKm);
			log.info("After Set Total Rate fetch"+ratesKm);
			
			trip.setTotalFare(totalDist*ratesKm);
			log.info("After Set Total Fare fetch"+totalDist*ratesKm);
			TripDetails response = tripService.createTrip(trip);

			return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
		}

		
/*
	// Complete Trip View Trip Details/
	@PostMapping("/CompleteTrip")
	public ResponseEntity<Object> completeTrip(@RequestBody TripDetails trip){
		log.info("Trip Id"+trip.getTrip_id());
		TripDetails completeTrip = tripService.getTripById(trip.getTrip_id());
		log.info("Trip Id"+completeTrip);
		if(trip==null) {
			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Trip Not Found","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Object>(completeTrip,HttpStatus.OK);
		
	}
	
*/
	// View Trip History Driver
	@PostMapping("/viewTripHistory")
	public  ResponseEntity<Object> TripHistoryDriver(@RequestBody DriverRequest driverRequest){
		CabDriver fetchDriver = driverService.fetchDriverByEmail(driverRequest.getEmail());
		if(fetchDriver==null) {
			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"driver not found","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		List<TripDetails> response = tripService.fetchTripByDriverEmail(driverRequest.getEmail());
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		
		
	// View Specific Trip 
	@PostMapping("/viewTrip")
	public ResponseEntity<Object> tripHistorySpecific(@RequestBody DriverRequest driverRequest){
		 TripDetails trip = tripService.getTripById(driverRequest.getId());
		 return new ResponseEntity<Object>(trip,HttpStatus.OK);
	 }


	// View Earning Driver
	@PostMapping("/totalEarningDriver")
	public  ResponseEntity<Object> getTotalEarningOfDriver(@RequestBody DriverRequest driverRequest)
	{
		CabDriver fetchDriver = driverService.fetchDriverByEmail(driverRequest.getEmail());
		if(fetchDriver!=null) {
		long totalEaring = tripService.calculateTotalEarning(driverRequest.getEmail());
		return new ResponseEntity<Object>(totalEaring,HttpStatus.OK);
		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"No Driver Found","409");
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
	
}

package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.Location;
import com.Cabbooking.CabBooking.Model.RatesAndTypes;
import com.Cabbooking.CabBooking.Model.TripDetails;
import com.Cabbooking.CabBooking.Repository.BookingRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Repository.TripRepository;
import com.Cabbooking.CabBooking.Request.CustomerRequest;
import com.Cabbooking.CabBooking.Request.EmailOtpRequest;
import com.Cabbooking.CabBooking.Request.SrcDestTypes;
import com.Cabbooking.CabBooking.Request.TripRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.BookingResponse;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.DriverDetailsResponse;
import com.Cabbooking.CabBooking.Response.TotalRatesAndCapacityResponse;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Service.AdminService;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.BookingCabService;
import com.Cabbooking.CabBooking.Service.CabService;
import com.Cabbooking.CabBooking.Service.CustomerService;
import com.Cabbooking.CabBooking.Service.DriverService;
import com.Cabbooking.CabBooking.Service.TripService;



@RestController
@RequestMapping("/Customer")
public class CustomerController {
	private static final Logger log = LoggerFactory.getLogger(DriverController.class);
	
	@Autowired
	AuthService authService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DriverService driverService;
	
	@Autowired
	CabService cabService;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	BookingCabService bookingCabService;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	TripService tripService;
	
	@Autowired
	AdminService adminService;

	
	
	
    // Fetch Customer By Email View Customer Profile
	@PostMapping("/getCustomer")
	public ResponseEntity<Object> getCustomer(@RequestBody CustomerRequest customerRequest)
		{
			Customer fetchCustomer = authService.fetchCustomerByEmail(customerRequest.getEmail());
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
			if(customer.getDateOfBirth()!="")
			{
				fetchCustomer.setDateOfBirth(customer.getDateOfBirth());
			}


			Customer response = customerService.updateCustomer(fetchCustomer);
			
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}

	
	//Get source location
	@GetMapping("/getSources")
	public ResponseEntity<Object> getSources(){
		List<String> response = adminService.getSource();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	//Get Destination location
	@GetMapping("/getDestination")
	public ResponseEntity<Object> getDestination(){
		List<String> response = adminService.getDestination();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	// Get Cab Type 
	@GetMapping("/getCabTypes")
	public ResponseEntity<Object> getCabTypes(){
		List<String> response = adminService.getCabTypes();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	//Set Cab Type
	@PostMapping("/setCabTypegetRates")
	public ResponseEntity<Object> setCabTypegetRates(@RequestBody SrcDestTypes srcDestTypes){
		Long fetchTotalDistance = adminService.fetchTotalDistanceByLocations(srcDestTypes.getSource(), srcDestTypes.getDestination());
		RatesAndTypes ratesAndCapacity = adminService.fetchRatesAndCapacity(srcDestTypes.getCabType());
		Long totalRate = fetchTotalDistance*(ratesAndCapacity.getRatekm());
		TotalRatesAndCapacityResponse response = new TotalRatesAndCapacityResponse(ratesAndCapacity.getCapacity(), totalRate);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}

	
	// Create Booking Customer // Cab Book
	@PostMapping("/bookCab")
	public ResponseEntity<Object> bookCab(@RequestBody Booking bookingDetails){
			
			if(bookingDetails != null) {
				Customer customer = customerService.fetchCustomerByEmail(bookingDetails.getCustomerEmail());
//				bookingDetails.setCustomerName(customer.getName());
//				bookingDetails.setCustContactNo(customer.getContactNo());
				bookingDetails.setStatus("0");
				Booking result = bookingCabService.bookCab(bookingDetails);
				BookingResponse response = new BookingResponse(new Date(),"Booking Confirmed","200",result,customer.getName(),customer.getContactNo(),result.getBooking_id());
				return new ResponseEntity<Object>(response,HttpStatus.CREATED);	

			}
			UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"No Booking Details","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			
		}

	
	
	//CHECK IF BOOKING ACCEPTED BY DRIVER
	@PostMapping("/checkBookingStatus")
	public ResponseEntity<Object> checkBookingStatus(@RequestBody BookingResponse bookingResponse){
		Booking booking = bookingCabService.getBookingById(bookingResponse.getBooking_id());
		if((booking.getStatus()).equals("1")) {
			Long driverId = booking.getDriverId();
			CabDriver fetchDriver = driverService.getById(driverId);
			DriverDetailsResponse response = new DriverDetailsResponse(new Date(),"Driver Accepted Request!!Driver Arriving","200",fetchDriver);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Searching For Driver!!!Booking Not Accepted!","409");
		return new ResponseEntity<Object>(response,HttpStatus.OK);

	}
	
	
	//View Trip Details Customer Side  After Complete Trip
	 @PostMapping("/viewTrip")
	 public ResponseEntity<Object> viewTrip(@RequestBody BookingResponse bookingResponse){
		 TripDetails trip = tripService.getTripByBookingId(bookingResponse.getBooking_id());
		 if(trip == null) {
			 UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Trip Not Completed!!!! OR Trip Not Found!!","409");
			 return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		 }
		 
		 return new ResponseEntity<Object>(trip,HttpStatus.OK);
	 }
	
	// View Trip History Customer
	@PostMapping("/viewCustomerTripHistory")
	public  ResponseEntity<Object> tripHistoryCustomer(@RequestBody TripRequest tripRequest){
		 Customer customer = customerService.fetchCustomerByEmail(tripRequest.getEmail());
		 log.info("After fetching customer "+customer);
			if(customer==null) {
				UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Customer not found","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			List<TripDetails> response = tripService.fetchTripByCustomerEmail(tripRequest.getEmail());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
			}


	//View Trip Details Customer Specific Trip
	@PostMapping("/viewSpecificTrip")
	public ResponseEntity<Object> viewSpecificTrip(@RequestBody TripRequest request){
			TripDetails trip = tripService.getTripById(request.getId());
			return new ResponseEntity<Object>(trip,HttpStatus.OK);
		 }

}

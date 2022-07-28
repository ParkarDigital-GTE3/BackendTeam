package com.AspasCabCustomer.Customer.Controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AspasCabCustomer.Customer.Model.Booking;
import com.AspasCabCustomer.Customer.Model.CabDriver;
import com.AspasCabCustomer.Customer.Model.Customer;
import com.AspasCabCustomer.Customer.Model.RatesAndTypes;
import com.AspasCabCustomer.Customer.Model.TripDetails;
import com.AspasCabCustomer.Customer.Request.CustomerRequest;
import com.AspasCabCustomer.Customer.Request.SrcDestTypes;
import com.AspasCabCustomer.Customer.Request.TripRequest;
import com.AspasCabCustomer.Customer.Request.UpdatePasswordRequest;
import com.AspasCabCustomer.Customer.Request.UpdateUserRequest;
import com.AspasCabCustomer.Customer.Response.BookingResponse;
import com.AspasCabCustomer.Customer.Response.CustomHistoryResponse;
import com.AspasCabCustomer.Customer.Response.CustomResponse;
import com.AspasCabCustomer.Customer.Response.CustomResponseForNoUser;
import com.AspasCabCustomer.Customer.Response.CustomerDetailsResponse;
import com.AspasCabCustomer.Customer.Response.CustomerResponseForInvalidLogin;
import com.AspasCabCustomer.Customer.Response.DriverDetailsResponse;
import com.AspasCabCustomer.Customer.Response.ResponseForTrip;
import com.AspasCabCustomer.Customer.Response.TotalRatesAndCapacityResponse;
import com.AspasCabCustomer.Customer.Service.AuthService;
import com.AspasCabCustomer.Customer.Service.BookingCabService;
import com.AspasCabCustomer.Customer.Service.CustomerService;
import com.AspasCabCustomer.Customer.Service.TripService;



@RestController
@RequestMapping("/login/customer")
@CrossOrigin
public class CustomerController {
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	AuthService authService;
	
	@Autowired
	CustomerService customerService;
	

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	BookingCabService bookingCabService;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	TripService tripService;
	

	
	
	
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
			CustomerDetailsResponse response = new CustomerDetailsResponse(new Date(), "Fetched Successfully", "200", fetchCustomer);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
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
				Customer fetchContact = authService.fetchCustomerByContactNo(customer.getContactNumber());
				if(fetchContact==null) {
				fetchCustomer.setContactNo(customer.getContactNumber());
			}else {
				CustomResponse response = new CustomResponse(new Date(), "Contact Number Already Exists", "409");
			}
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
		List<String> response = customerService.getSource();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	//Get Destination location
	@GetMapping("/getDestination")
	public ResponseEntity<Object> getDestination(){
		List<String> response = customerService.getDestination();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	// Get Cab Type 
	@GetMapping("/getCabTypes")
	public ResponseEntity<Object> getCabTypes(){
		List<String> response = customerService.getCabTypes();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	//Set Cab Type
	@PostMapping("/setCabTypegetRates")
	public ResponseEntity<Object> setCabTypegetRates(@RequestBody SrcDestTypes srcDestTypes){
		Long fetchTotalDistance = customerService.fetchTotalDistanceByLocations(srcDestTypes.getSource(), srcDestTypes.getDestination());
		Long ratekm = customerService.fetchRates(srcDestTypes.getCabType());
		String capacity = customerService.fetchCapcity(srcDestTypes.getCabType());
		Long totalRate = fetchTotalDistance*(ratekm);
		TotalRatesAndCapacityResponse response = new TotalRatesAndCapacityResponse("200",capacity, totalRate);
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
			CustomResponse response = new CustomResponse(new Date(),"No Booking Details","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			
		}

	
	
	//CHECK IF BOOKING ACCEPTED BY DRIVER
	@PostMapping("/checkBookingStatus")
	public ResponseEntity<Object> checkBookingStatus(@RequestBody BookingResponse bookingResponse){
		Booking booking = bookingCabService.getBookingById(bookingResponse.getBooking_id());
		if((booking.getStatus()).equals("1")) {
			Long driverId = booking.getDriverId();
			CabDriver fetchDriver = customerService.getDriverById(driverId);
			DriverDetailsResponse response = new DriverDetailsResponse(new Date(),"Driver Accepted Request!!Driver Arriving","200",fetchDriver);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		CustomResponse response = new CustomResponse(new Date(),"Searching For Driver!!!Booking Not Accepted!","409");
		return new ResponseEntity<Object>(response,HttpStatus.OK);

	}
	
	
	//View Trip Details Customer Side  After Complete Trip
	 @PostMapping("/viewTrip")
	 public ResponseEntity<Object> viewTrip(@RequestBody BookingResponse bookingResponse){
		 TripDetails trip = tripService.getTripByBookingId(bookingResponse.getBooking_id());
		 if(trip == null) {
			 CustomResponse response = new CustomResponse(new Date(),"Trip Not Completed!!!! OR Trip Not Found!!","409");
			 return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		 }
		 ResponseForTrip response = new ResponseForTrip(new Date(),"Trip Completed!!!","200",trip);
		 return new ResponseEntity<Object>(response,HttpStatus.OK);
	 }
	
	// View Trip History Customer
	@PostMapping("/viewCustomerTripHistory")
	public  ResponseEntity<Object> tripHistoryCustomer(@RequestBody TripRequest tripRequest){
		 Customer customer = customerService.fetchCustomerByEmail(tripRequest.getEmail());
		 log.info("After fetching customer "+customer);
			if(customer==null) {
				CustomResponse response = new CustomResponse(new Date(),"Customer not found","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			List<TripDetails> trips = tripService.fetchTripByCustomerEmail(tripRequest.getEmail());
			CustomHistoryResponse response = new CustomHistoryResponse("200",trips);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
			}


	//View Trip Details Customer Specific Trip
	@PostMapping("/viewSpecificTrip")
	public ResponseEntity<Object> viewSpecificTrip(@RequestBody TripRequest request){
			TripDetails trip = tripService.getTripById(request.getId());
			ResponseForTrip response = new ResponseForTrip(new Date(),"Fetched Succesfully!!!","200",trip);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		 }

}

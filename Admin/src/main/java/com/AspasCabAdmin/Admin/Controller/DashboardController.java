package com.AspasCabAdmin.Admin.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AspasCabAdmin.Admin.Model.CabDetails;
import com.AspasCabAdmin.Admin.Model.CabDriver;
import com.AspasCabAdmin.Admin.Model.Customer;
import com.AspasCabAdmin.Admin.Model.Location;
import com.AspasCabAdmin.Admin.Model.RatesAndTypes;
import com.AspasCabAdmin.Admin.Model.TripDetails;
import com.AspasCabAdmin.Admin.Request.TripRequest;
import com.AspasCabAdmin.Admin.Response.CabDetailsResponse;
import com.AspasCabAdmin.Admin.Response.CustomCountResponse;
import com.AspasCabAdmin.Admin.Response.CustomLocationResponse;
import com.AspasCabAdmin.Admin.Response.CustomResponse;
import com.AspasCabAdmin.Admin.Response.CustomTripsResponse;
import com.AspasCabAdmin.Admin.Response.CustomTypeResponse;
import com.AspasCabAdmin.Admin.Response.CustomerDetailsResponse;
import com.AspasCabAdmin.Admin.Response.DriverDetailsResponse;
import com.AspasCabAdmin.Admin.Response.TripDetailsResponse;
import com.AspasCabAdmin.Admin.Response.TripHistoryDetailsResponse;
import com.AspasCabAdmin.Admin.Service.AdminService;


@RestController
@RequestMapping("/login")
@CrossOrigin
public class DashboardController {

	@Autowired
	AdminService adminService;
	
	 
	@PostMapping("/showCabtypes")
	public ResponseEntity<Object> showCabtypes(){
		List<RatesAndTypes> rateantype = adminService.fetchAllTypes();
		CustomTypeResponse response = new CustomTypeResponse("Fetched Succesfully","200",rateantype);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	@PostMapping("/showLocations")
	public ResponseEntity<Object> showLocations(){
		List<Location> location = adminService.fetchAllLocations();
		CustomLocationResponse response = new CustomLocationResponse("Fetched Succesfully","200",location);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 //View Trips History
	 @PostMapping("/viewAllTripHistory")
	 public ResponseEntity<Object> tripHistory(){
		 List<TripDetails> trips = adminService.findAllTrips();
		 if (trips == null)
		  {
		  	CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
		  	return new ResponseEntity<Object>(response,HttpStatus.OK);
		  }
		  	TripHistoryDetailsResponse response = new TripHistoryDetailsResponse(new Date(),"Fetched Succesfully!!!","200",trips);
		 return new ResponseEntity<Object>(response,HttpStatus.OK);
	 }
	
	 
	 // View Specific Trip()
	 @PostMapping("/viewTrip")
	 public ResponseEntity<Object> getSpecificTrip(@RequestBody TripRequest tripRequest){
		 TripDetails trip = adminService.getTripById(tripRequest.getId());
		 if (trip == null)
		  {
		  	CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
		  	return new ResponseEntity<Object>(response,HttpStatus.OK);
		  }
		  	TripDetailsResponse response = new TripDetailsResponse(new Date(),"Fetched Succesfully!!!","200",trip);
		 return new ResponseEntity<Object>(response,HttpStatus.OK);
	 }



	// Fetch All Customer
	@PostMapping("/getCustomers")
	public ResponseEntity<Object> getCustomers()
	 {
	  List<Customer> fetchCustomer = adminService.fetchAllCustomers();
	  if (fetchCustomer == null)
	  {
	  	CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
	  	return new ResponseEntity<Object>(response,HttpStatus.OK);
	  }
	  	CustomerDetailsResponse response = new CustomerDetailsResponse(new Date(),"Fetched Succesfully!!!","200",fetchCustomer);
	  return new ResponseEntity<Object>(response,HttpStatus.OK);
	 }

	
	// Fetch  All Driver
	 @PostMapping("/getCabDrivers")
	 public ResponseEntity<Object> getCabDrivers()
	 	{
	 		List<CabDriver> fetchDriver = adminService.fetchAllDrivers();
	 		if (fetchDriver == null)
	 		{
	 			CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
	 			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
	 		}
	 		DriverDetailsResponse response = new DriverDetailsResponse(new Date(),"Fetched Succesfully!!!","200",fetchDriver);
	 		return new ResponseEntity<Object>(response,HttpStatus.OK);
	 	}	

	 
	 // Fetch Cabs
	 @PostMapping("/getCabs")
	 public ResponseEntity<Object> getCabs(){
		 List<CabDetails> cabs = adminService.findAllCabs();
		 if (cabs == null)
	 		{
	 			CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
	 			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
	 		}
	 		CabDetailsResponse response = new CabDetailsResponse(new Date(),"Fetched Succesfully!!!","200",cabs);
	 		return new ResponseEntity<Object>(response,HttpStatus.OK);
	 }

	
	@PostMapping("/driverCount")
	public ResponseEntity<Object> countDriver()
	{
		Long count =  adminService.countDriver();	
		CustomCountResponse response = new CustomCountResponse(new Date(),"200",count);

		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
		
	 // Count customers
	@PostMapping("/customerCount")
	public ResponseEntity<Object> countCustomers()
	{
		 
		Long count =  adminService.countCustomer();	
		CustomCountResponse response = new CustomCountResponse(new Date(),"200",count);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "hi";
	}

	 // View Earning Adminside
	 @PostMapping("/totalEarning")
	public ResponseEntity<Object> totalfare() {
		 Long earn = adminService.fetchTotalEarning();
		 CustomCountResponse response = new CustomCountResponse(new Date(),"200",earn);
		 return new ResponseEntity<Object>(response,HttpStatus.OK);
		
		}	
	 
	 //View Todays Earning 
	@PostMapping("/todayTotalsEarning")
	public ResponseEntity<Object> todayTotalsEarning(@RequestBody TripRequest tripRequest) {
		Long earn = adminService.fetchTodaysTotalEarning(tripRequest.getDate()); 
		CustomCountResponse response = new CustomCountResponse(new Date(),"200",earn);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	 //View Trips Date wise //
	@PostMapping("/viewTripDateWise")
	public ResponseEntity<Object> viewTripDateWise(){
		List<String> trips = adminService.countTripByDate();
		CustomTripsResponse response = new CustomTripsResponse(new Date(),"200",trips);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 
	 // View Trip Location wise //
	@PostMapping("/showTripsLocationwise")
	public ResponseEntity<Object> CountTripByLocation() {
	 List<String> trips = adminService.CountTripByLocation();
	 CustomTripsResponse response = new CustomTripsResponse(new Date(),"200",trips);
	return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 

}

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
import com.AspasCabAdmin.Admin.Model.TripDetails;
import com.AspasCabAdmin.Admin.Request.TripRequest;
import com.AspasCabAdmin.Admin.Response.CustomResponse;
import com.AspasCabAdmin.Admin.Service.AdminService;


@RestController
@RequestMapping("/adminLogin")
@CrossOrigin
public class DashboardController {

	@Autowired
	AdminService adminService;
	
	 
	 //View Trips History
	 @GetMapping("/viewAllTripHistory")
	 public ResponseEntity<Object> tripHistory(){
		 List<TripDetails> trips = adminService.findAllTrips();
		 return new ResponseEntity<Object>(trips,HttpStatus.OK);
	 }
	
	 
	 // View Specific Trip()
	 @PostMapping("/viewTrip")
	 public ResponseEntity<Object> getSpecificTrip(@RequestBody TripRequest tripRequest){
		 TripDetails trip = adminService.getTripById(tripRequest.getId());
		 return new ResponseEntity<Object>(trip,HttpStatus.OK);
	 }



	// Fetch All Customer
	@GetMapping("/getCustomers")
	public ResponseEntity<Object> getCustomers()
	 {
	  List<Customer> fetchCustomer = adminService.fetchAllCustomers();
	  if (fetchCustomer == null)
	  {
	  	CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
	  	return new ResponseEntity<Object>(response,HttpStatus.OK);
	  }
	  		
	  return new ResponseEntity<Object>(fetchCustomer,HttpStatus.OK);
	 }

	
	// Fetch  All Driver
	 @GetMapping("/getCabDrivers")
	 public ResponseEntity<Object> getCabDrivers()
	 	{
	 		List<CabDriver> fetchDriver = adminService.fetchAllDrivers();
	 		if (fetchDriver == null)
	 		{
	 			CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
	 			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
	 		}
	 		
	 		return new ResponseEntity<Object>(fetchDriver,HttpStatus.OK);
	 	}	

	 
	 // Fetch Cabs
	 @GetMapping("/getCabs")
	 public ResponseEntity<Object> getCabs(){
		 List<CabDetails> cabs = adminService.findAllCabs();
		 return new ResponseEntity<Object>(cabs,HttpStatus.OK);
	 }

	
	@GetMapping("/DriverCount")
	public ResponseEntity<Object> countDriver()
	{
		Long response =  adminService.countDriver();	
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
		
	 // Count customers
	@GetMapping("/CustomerCount")
	public ResponseEntity<Object> countCustomers()
	{
		 
		Long response =  adminService.countCustomer();	
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 

	 // View Earning Adminside
	 @GetMapping("/totalEarning")
	public ResponseEntity<Object> totalfare() {
		 Long resonse = adminService.fetchTotalEarning();
		 return new ResponseEntity<Object>(resonse,HttpStatus.OK);
		}	
	 
	 //View Todays Earning 
	@PostMapping("/todayTotalsEarning")
	public ResponseEntity<Object> todayTotalsEarning(@RequestBody TripRequest tripRequest) {
		Long response = adminService.fetchTodaysTotalEarning(tripRequest.getDate()); 
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	 //View Trips Date wise //
	@GetMapping("/viewTripDateWise")
	public ResponseEntity<Object> viewTripDateWise(){
		List<String> response = adminService.countTripByDate();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 
	 // View Trip Location wise //
	@GetMapping("/showTripsLocationwise")
	public ResponseEntity<Object> CountTripByLocation() {
	 List<String> response = adminService.CountTripByLocation();
	return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 

}

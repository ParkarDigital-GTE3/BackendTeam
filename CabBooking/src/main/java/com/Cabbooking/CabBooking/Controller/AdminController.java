package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.Admin;
import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.Location;
import com.Cabbooking.CabBooking.Model.RatesAndTypes;
import com.Cabbooking.CabBooking.Model.TripDetails;
import com.Cabbooking.CabBooking.Repository.AdminRepository;
import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.CarTypesAndRatesRepo;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Repository.LocationRepository;
import com.Cabbooking.CabBooking.Repository.TripRepository;
import com.Cabbooking.CabBooking.Request.TripDateWiseRequest;
import com.Cabbooking.CabBooking.Request.TripRequest;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.LocationWiseTrips;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.AdminService;
import com.Cabbooking.CabBooking.Service.AuthService;
import com.Cabbooking.CabBooking.Service.CabService;
import com.Cabbooking.CabBooking.Service.CustomerService;
import com.Cabbooking.CabBooking.Service.DriverService;
import com.Cabbooking.CabBooking.Service.TripService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DriverService driverService;
	
	@Autowired
	CabService cabService;
	
	@Autowired
	TripService tripService;
	
	@Autowired
	AuthService authService;
	
	
	// Admin Login
	@PostMapping("/adminLogin")
	public ResponseEntity<Object> adminLogin(@RequestBody Admin admin){
		Admin fetchAdmin = adminService.getByUsername(admin.getUserName());
		if(fetchAdmin != null) {
			if ((fetchAdmin.getPassword()).equals(admin.getPassword())) {
				UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Login Successfully","200");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
				
			}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Incorrect Credentials","409");
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);

		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Admin Not Found","409");
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);

	}

	
	// Get Newly Registered Drivers
	@GetMapping("/getDriverRegistrations")
	public ResponseEntity<Object> getDriverRegistrations(){
		List<CabDriver> response = driverService.findAllByActivationStatus();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}

	
	// Verify Drivers
	@PostMapping("/updateDriverRegistration")
	public ResponseEntity<Object> updateDriverRegistration(@RequestBody CabDriver cabDriverDetails){
		CabDriver driver = authService.fetchDriverByEmail(cabDriverDetails.getEmail());
		if(driver != null) {
			driver.setActivationStatus("1");
			CabDriver response = driverService.updateDriverRegistration(driver);
			return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(), "Driver not registered", "409");
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		
	}

	
	//Set Location
	@PostMapping("/setLocations")
	public ResponseEntity<Object> addLocation(@RequestBody Location location){
		Location response = adminService.setLocation(location);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	// Set Cab Types
	@PostMapping("/addCarTypes")
	public ResponseEntity<Object>addCarType(@RequestBody RatesAndTypes cabTypes) {
		RatesAndTypes response = adminService.addCarType(cabTypes);
			return new ResponseEntity<Object>(response, HttpStatus.OK);		
	}
	
	
	// Fetch All Customer
	@GetMapping("/getCustomers")
	public ResponseEntity<Object> getCustomers()
	 {
	  List<Customer> fetchCustomer = customerService.fetchAllCustomers();
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
	 		List<CabDriver> fetchDriver = driverService.fetchAllDrivers();
	 		if (fetchDriver == null)
	 		{
	 			CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authentication","409");
	 			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
	 		}
	 		
	 		return new ResponseEntity<Object>(fetchDriver,HttpStatus.OK);
	 	}	

	 
	 // Fetch Cabs
	 @GetMapping("/getCabs")
	 public ResponseEntity<Object> getCabs(){
		 List<CabDetails> cabs = cabService.findAllCabs();
		 return new ResponseEntity<Object>(cabs,HttpStatus.OK);
	 }

	 
	 //View Trips History
	 @GetMapping("/viewAllTripHistory")
	 public ResponseEntity<Object> tripHistory(){
		 List<TripDetails> trips = tripService.findAllTrips();
		 return new ResponseEntity<Object>(trips,HttpStatus.OK);
	 }
	
	 
	 // View Specific Trip()
	 @PostMapping("/viewTrip")
	 public ResponseEntity<Object> getSpecificTrip(@RequestBody TripRequest tripRequest){
		 TripDetails trip = tripService.getTripById(tripRequest.getId());
		 return new ResponseEntity<Object>(trip,HttpStatus.OK);
	 }


	 // View Earning Adminside
	 @GetMapping("/totalEarning")
	public ResponseEntity<Object> totalfare() {
		 Long resonse = tripService.fetchTotalEarning();
		 return new ResponseEntity<Object>(resonse,HttpStatus.OK);
		}	
	 
	 //View Todays Earning 
	@PostMapping("/todayTotalsEarning")
	public ResponseEntity<Object> todayTotalsEarning(@RequestBody TripRequest tripRequest) {
		Long response = tripService.fetchTodaysTotalEarning(tripRequest.getDate()); 
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		}


	 
	 
	 
	 // DASHBOARD 
	 
	//Count Drivers
	@GetMapping("/DriverCount")
	public Long countDriver()
	{
		return driverService.countDriver();	
	}
		
	 // Count customers
	@GetMapping("/CustomerCount")
	public Long countCustomers()
	{
		return customerService.countCustomer();	
	}
	 
	
	
	 //View Trips Date wise //
	@GetMapping("/viewTripDateWise")
	public ResponseEntity<Object> viewTripDateWise(){
		List<TripDateWiseRequest> response = tripService.countTripByDate();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 
/*	 // View Trip Location wise //
	@GetMapping("/showTripsLocationwise")
	public ResponseEntity<Object> CountTripByLocation() {
	 List<LocationWiseTrips> response = tripService.CountTripByLocation();
	return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	 
*/
	 

}

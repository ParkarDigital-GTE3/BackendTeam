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
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.AuthService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	CarTypesAndRatesRepo carTypesAndRatesRepo;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	TripRepository tripRepository;
	
	@Autowired
	AuthService authService;
	
	// Admin Login
	@PostMapping("/adminLogin")
	public ResponseEntity<Object> adminLogin(@RequestBody Admin admin){
		Admin fetchAdmin = adminRepository.getByUsername(admin.getUserName());
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
		List<CabDriver> response = driverRepository.findAllByActivationStatus();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	// Verify Drivers
	@PostMapping("/updateDriverRegistration")
	public ResponseEntity<Object> updateDriverRegistration(@RequestBody CabDriver cabDriverDetails){
		CabDriver driver = authService.fetchDriverByEmail(cabDriverDetails.getEmail());
		if(driver != null) {
			driver.setActivationStatus("1");
			CabDriver response = driverRepository.save(driver);
			return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(), "Driver not registered", "409");
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		
	}
	//Set Location
	@PostMapping("/setLocations")
	public ResponseEntity<Object> addLocation(@RequestBody Location location){
		Location response = locationRepo.save(location);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	// Set Cab Types
	@PostMapping("/addCarTypes")
	public ResponseEntity<Object>addCarType(@RequestBody RatesAndTypes cabTypes) {
		carTypesAndRatesRepo.save(cabTypes);
		
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Added", "200");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
			
	}
	
	
	// Fetch All Customer
	@GetMapping("/getCustomers")
	public ResponseEntity<Object> getCustomers()
	 {
	  List<Customer> fetchCustomer = customerRepository.findAll();
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
	 		List<CabDriver> fetchDriver = driverRepository.findAll();
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
		 List<CabDetails> cabs = cabRepository.findAll();
		 return new ResponseEntity<Object>(cabs,HttpStatus.OK);
	 }
	 
	 //View Trips History
	 @GetMapping("/viewAllTripHistory")
	 public ResponseEntity<Object> tripHistory(){
		 List<TripDetails> trips = tripRepository.findAll();
		 return new ResponseEntity<Object>(trips,HttpStatus.OK);
	 }
	
	 
	 // View Specific Trip()
	 @GetMapping("/viewTrip/{id}")
	 public ResponseEntity<Object> tripHistorySpecific(@PathVariable("id") long id){
		 TripDetails trip = tripRepository.getById(id);
		 return new ResponseEntity<Object>(trip,HttpStatus.OK);
	 }

/* cd
	 // View Earning Adminside()// Gaurav // View All time Earning 
	 @GetMapping("/totalEarning")
	public Long  totalfare() {
		return tripRepository.totalEarning();
			 	
		}	
	 
	 //View Todays Earning //Vaishnavi
	@GetMapping("/todayFareTotals/{date}")
	public Long selectTotals(@PathVariable("date") int date) {
		return tripRepository.todaysEarning(date); 
		}
	
*/
	 // DASHBOARD 
	 
	 //Count Drivers// Girish
		@GetMapping("/DriverCount")
		public Long countDriver()
		{
			return driverRepository.countDriver();	
		}
		
	 // Count customers // Girish
		@GetMapping("/CustomerCount")
		public Long countCustomers()
		{
			return customerRepository.countCustomer();	
		}
	 
	 //View Trips Date wise //
	 
	 // View Trip Location wise //
	 
	 

	 

}

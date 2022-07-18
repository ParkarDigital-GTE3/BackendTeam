package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.AuthService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	AuthService authService;
	
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
}

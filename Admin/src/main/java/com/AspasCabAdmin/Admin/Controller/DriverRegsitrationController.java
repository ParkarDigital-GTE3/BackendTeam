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

import com.AspasCabAdmin.Admin.Model.CabDriver;
import com.AspasCabAdmin.Admin.Response.CustomResponse;
import com.AspasCabAdmin.Admin.Response.DriverDetailsResponse;
import com.AspasCabAdmin.Admin.Response.TripDetailsResponse;
import com.AspasCabAdmin.Admin.Service.AdminService;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class DriverRegsitrationController {

	@Autowired
	AdminService adminService;
	
	// Get Newly Registered Drivers
		@PostMapping("/getDriverRegistrations")
		public ResponseEntity<Object> getDriverRegistrations(){
			List<CabDriver> drivers = adminService.findAllByActivationStatus();
			if (drivers == null)
			  {
			  	CustomResponse response = new CustomResponse(new Date(),"Error in authentication","409");
			  	return new ResponseEntity<Object>(response,HttpStatus.OK);
			  }
			DriverDetailsResponse response = new DriverDetailsResponse(new Date(),"Fetched Succesfully!!!","200",drivers);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}

				// Verify Drivers
		@PostMapping("/updateDriverRegistration")
		public ResponseEntity<Object> updateDriverRegistration(@RequestBody CabDriver cabDriverDetails){
			CabDriver driver = adminService.fetchDriverById(cabDriverDetails.getDriver_id());
			if(driver != null) {
				driver.setActivationStatus("1");
				CabDriver cabdriver = adminService.updateDriverRegistration(driver);
				CustomResponse response = new CustomResponse(new Date(), "Driver  activated!!", "200");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			CustomResponse response = new CustomResponse(new Date(), "Driver not registered", "409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			
		}

		
}

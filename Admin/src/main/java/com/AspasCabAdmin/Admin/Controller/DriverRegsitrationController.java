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
import com.AspasCabAdmin.Admin.Service.AdminService;

@RestController
@RequestMapping("/adminLogin")
@CrossOrigin
public class DriverRegsitrationController {

	@Autowired
	AdminService adminService;
	
	// Get Newly Registered Drivers
		@GetMapping("/getDriverRegistrations")
		public ResponseEntity<Object> getDriverRegistrations(){
			List<CabDriver> response = adminService.findAllByActivationStatus();
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}

		
		// Verify Drivers
		@PostMapping("/updateDriverRegistration")
		public ResponseEntity<Object> updateDriverRegistration(@RequestBody CabDriver cabDriverDetails){
			CabDriver driver = adminService.fetchDriverByEmail(cabDriverDetails.getEmail());
			if(driver != null) {
				driver.setActivationStatus("1");
				CabDriver response = adminService.updateDriverRegistration(driver);
				return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
			}
			CustomResponse response = new CustomResponse(new Date(), "Driver not registered", "409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			
		}

		
}

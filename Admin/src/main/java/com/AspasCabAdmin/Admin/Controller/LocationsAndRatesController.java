package com.AspasCabAdmin.Admin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AspasCabAdmin.Admin.Model.Location;
import com.AspasCabAdmin.Admin.Model.RatesAndTypes;
import com.AspasCabAdmin.Admin.Service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class LocationsAndRatesController {


	@Autowired
	AdminService adminService;
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
	
}

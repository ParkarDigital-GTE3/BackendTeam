package com.AspasCabAdmin.Admin.Controller;

import java.util.Date;

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
import com.AspasCabAdmin.Admin.Response.CustomResponse;
import com.AspasCabAdmin.Admin.Service.AdminService;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LocationsAndRatesController {


	@Autowired
	AdminService adminService;
	//Set Location
	@PostMapping("/setLocations")
	public ResponseEntity<Object> addLocation(@RequestBody Location location){
		Location fetchlocation = adminService.fetchLocationBySrcAndDest(location.getSource(),location.getDestination());
		if(fetchlocation==null) {
		Location loc = adminService.setLocation(location);
		if(loc!=null) {
			CustomResponse response = new CustomResponse(new Date(),"Location Added","200");
			return new ResponseEntity<Object>(response, HttpStatus.OK);		
		}
			CustomResponse response = new CustomResponse(new Date(),"Location Not Added","409");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		CustomResponse response = new CustomResponse(new Date(),"Location Already Exists!!","403");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
	
	// Set Cab Types
	@PostMapping("/addCarTypes")
	public ResponseEntity<Object>addCarType(@RequestBody RatesAndTypes cabTypes) {
		RatesAndTypes fetchType = adminService.fetchCabBytypeAndCapacity(cabTypes.getType(),cabTypes.getCapacity());
		if(fetchType == null) {
		RatesAndTypes rate = adminService.addCarType(cabTypes);
		if(rate!=null) {
		CustomResponse response = new CustomResponse(new Date(),"Cab Type Added","200");
		return new ResponseEntity<Object>(response, HttpStatus.OK);		
	}
		CustomResponse response = new CustomResponse(new Date(),"Cab Not Added","409");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
		CustomResponse response = new CustomResponse(new Date(),"Cab Type Already Exists","403");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
	
}

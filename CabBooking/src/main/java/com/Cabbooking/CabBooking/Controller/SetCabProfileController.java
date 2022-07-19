package com.Cabbooking.CabBooking.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Request.UpdateCabRequest;
import com.Cabbooking.CabBooking.Response.CabDetailsResponse;
import com.Cabbooking.CabBooking.Response.CustomCabResponse;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.AuthService;

@RequestMapping("/login/Cabs")
@RestController
public class SetCabProfileController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	
	
	
	// Set Cab Profile
	@PostMapping("/setCabDetails")
	public ResponseEntity<Object> setCabDetails(@RequestBody CabDetails cabDetails)
	{
		CabDetails fetchCab = authService.fetchCabByRegistrationNo(cabDetails.getCabRegistrationNo());
		CabDriver driver = authService.fetchDriverById(cabDetails.getDriver_id().getDriver_id());
		if(fetchCab == null) {
			cabDetails.setDriver_id(driver);
			CabDetails cab = authService.createCab(cabDetails);
//			CabDetails setCab = cabRepository.getById(cab.getCab_id());
//			driver.setCab_id(setCab);
//			driverRepository.save(driver);
			CabDetailsResponse response = new CabDetailsResponse(new Date(), "Cab Created Succesfully", "200",cab);
			CabDetails updatedCab = authService.fetchCabById(cab.getCab_id());
			driver.setCab_id(updatedCab);
			CabDriver updatedDriverCabId = authService.updateDriverCabId(driver);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Already Cab Exists!!","401");
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}

	
	//Get Cab Details
	@GetMapping("/getCabDetails/{RegistrationNo}")
	public ResponseEntity<Object> getCabDetails(@PathVariable("RegistrationNo") String RegistrationNo)
		{
			System.out.println(RegistrationNo);
			CabDetails fetchCab = authService.fetchCabByRegistrationNo(RegistrationNo);
			if (fetchCab == null)
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Cab Not Registered","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			CabDriver driver = authService.fetchDriverById(fetchCab.getDriver_id().getDriver_id());
			CustomCabResponse response = new CustomCabResponse(new Date(), "Cab Found", "200",driver,fetchCab);
			return new ResponseEntity<Object>(fetchCab,HttpStatus.OK);
		}
	
	
	// Update Cab Details
	@PostMapping("/updateCabDetails")
	public ResponseEntity<Object> updateCabDetails(@RequestBody UpdateCabRequest cab)
		{
			CabDetails fetchCab = authService.fetchCabByRegistrationNo(cab.getCabRegistrationNo());

			if (fetchCab == null)
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Cab Not Registered","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			
			if(cab.getCabRegistrationNo()!="")
			{
				fetchCab.setCabRegistrationNo(cab.getCabRegistrationNo());
			}
			if(cab.getCabInsuranceNo()!="")
			{
				fetchCab.setCabInsuranceNo(cab.getCabInsuranceNo());
			}
			if(cab.getCabType()!="")
			{
				fetchCab.setCabType(cab.getCabType());
			}
			if(cab.getCabCapacity()!="")
			{
				fetchCab.setCabCapacity(cab.getCabCapacity());
			}
			if(cab.getLicenseNo()!="")
			{
				fetchCab.setLicenseNo(cab.getLicenseNo());
			}
			
			cabRepository.save(fetchCab);
			CabDetails result = fetchCab;
			CabDetailsResponse response = new CabDetailsResponse(new Date(),"Successfully Updated","200",result);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}


}

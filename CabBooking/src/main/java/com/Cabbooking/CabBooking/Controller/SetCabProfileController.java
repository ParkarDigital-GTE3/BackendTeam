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
import com.Cabbooking.CabBooking.Service.CabService;
import com.Cabbooking.CabBooking.Service.CustomerService;
import com.Cabbooking.CabBooking.Service.DriverService;

@RequestMapping("/DriverSetCab")
@RestController
public class SetCabProfileController {
	
	@Autowired
	CabService cabService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	DriverService driverService;
	
	@Autowired
	CustomerService customerService;
	
	
	
	
	// Set Cab Profile
	@PostMapping("/setCabDetails/{driver_email}")
	public ResponseEntity<Object> setCabDetails(@RequestBody CabDetails cabDetails,@PathVariable("driver_email") String driver_email)
	{
		CabDetails fetchCab = cabService.fetchCabByRegistrationNo(cabDetails.getCabRegistrationNo());
//		CabDriver driver = authService.fetchDriverById(cabDetails.getDriver_id().getDriver_id());
		CabDriver driver = authService.fetchDriverByEmail(driver_email);
		if(fetchCab == null) {
//			cabDetails.setDriver_id(driver);
			CabDetails cab = cabService.createCab(cabDetails);
			driver.setCab_id(cab);
			driverService.updateDriver(driver);
			CabDetailsResponse response = new CabDetailsResponse(new Date(), "Cab Created Succesfully", "200",driver);
//			CabDetails updatedCab = authService.fetchCabById(cab.getCab_id());
//			driver.setCab_id(updatedCab);
//			CabDriver updatedDriverCabId = authService.updateDriverCabId(driver);
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
			CabDetails fetchCab = cabService.fetchCabByRegistrationNo(RegistrationNo);
			if (fetchCab == null)
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Cab Not Registered","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			//CabDriver driver = authService.fetchDriverById(fetchCab.getDriver_id().getDriver_id());
			CustomCabResponse response = new CustomCabResponse(new Date(), "Cab Found", "200",fetchCab);
			return new ResponseEntity<Object>(fetchCab,HttpStatus.OK);
		}
	
	
	// Update Cab Details
	@PostMapping("/updateCabDetails")
	public ResponseEntity<Object> updateCabDetails(@RequestBody UpdateCabRequest cab)
		{
			CabDetails fetchCab = cabService.fetchCabByRegistrationNo(cab.getCabRegistrationNo());

			if (fetchCab == null)
			{
				CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Cab Not Registered","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
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
			
			
			CabDetails result = cabService.updateCab(fetchCab);
			CustomCabResponse response = new CustomCabResponse(new Date(),"Successfully Updated","200",result);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}


}

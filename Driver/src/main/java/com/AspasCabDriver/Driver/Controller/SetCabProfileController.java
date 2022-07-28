package com.AspasCabDriver.Driver.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AspasCabDriver.Driver.Model.CabDetails;
import com.AspasCabDriver.Driver.Model.CabDriver;
import com.AspasCabDriver.Driver.Request.UpdateCabRequest;
import com.AspasCabDriver.Driver.Response.CabDetailsResponse;
import com.AspasCabDriver.Driver.Response.CustomCabResponse;
import com.AspasCabDriver.Driver.Response.CustomResponse;
import com.AspasCabDriver.Driver.Service.AuthService;
import com.AspasCabDriver.Driver.Service.CabService;
import com.AspasCabDriver.Driver.Service.DriverService;


@RestController
@RequestMapping("/login/driver/SetCab")
@CrossOrigin
public class SetCabProfileController {
	
	@Autowired
	CabService cabService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	DriverService driverService;

	
	// Set Cab Profile
	@PostMapping("/setCabDetails")
	public ResponseEntity<Object> setCabDetails(@RequestBody CabDetails cabDetails)
	{
		CabDetails fetchCab = cabService.fetchCabByRegistrationNo(cabDetails.getCabRegistrationNo());
//		CabDriver driver = authService.fetchDriverById(cabDetails.getDriver_id().getDriver_id());
		CabDriver driver = authService.fetchDriverByEmail(cabDetails.getDriverEmail());
		if(driver != null) {
			CabDetails fetchLicense = cabService.fetchCabByLicenseNo(cabDetails.getLicenseNo());
			if(fetchLicense == null) {
				CabDetails fetchInsurance = cabService.fetchCabByInsuranceNo(cabDetails.getCabInsuranceNo());
				if(fetchInsurance == null) {
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
						CustomResponse response = new CustomResponse(new Date(),"Already Cab Exists!!","401");
						return new ResponseEntity<Object>(response,HttpStatus.OK);
				}
				else {
					CustomResponse response = new CustomResponse(new Date(),"Insurance Number Already Exists!!","401");
					return new ResponseEntity<Object>(response,HttpStatus.OK);
				}
			}
			else {
				CustomResponse response = new CustomResponse(new Date(),"License Number Already exists!!","401");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}

	}
		CustomResponse response = new CustomResponse(new Date(),"Driver Not Found!!","401");
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		
	}

	
	//Get Cab Details
	@PostMapping("/getCabDetails")
	public ResponseEntity<Object> getCabDetails(@RequestBody UpdateCabRequest getCabRequest)
		{
			CabDetails fetchCab = cabService.fetchCabByEmail(getCabRequest.getDriverEmail());
			
			if (fetchCab == null)
			{
				CustomResponse response = new CustomResponse(new Date(),"Cab Not Registered","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
			//CabDriver driver = authService.fetchDriverById(fetchCab.getDriver_id().getDriver_id());
			CustomCabResponse response = new CustomCabResponse(new Date(), "Cab Found", "200",fetchCab);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	// Update Cab Details
	@PostMapping("/updateCabDetails")
	public ResponseEntity<Object> updateCabDetails(@RequestBody UpdateCabRequest cab)
		{
			CabDetails fetchCab = cabService.fetchCabByRegistrationNo(cab.getCabRegistrationNo());

			if (fetchCab == null)
			{
				CustomResponse response = new CustomResponse(new Date(),"Cab Not Registered","409");
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
			if(cab.getCab_name()!="")
			{
				fetchCab.setCab_name(cab.getCab_name());
			}
			
			
			CabDetails result = cabService.updateCab(fetchCab);
			CustomCabResponse response = new CustomCabResponse(new Date(),"Successfully Updated","200",result);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}


}

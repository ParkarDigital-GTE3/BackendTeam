package com.Cabbooking.CabBooking.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Request.UpdateCabRequest;
import com.Cabbooking.CabBooking.Request.UpdatePasswordRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CabDetailsResponse;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.CustomerDetailsResponse;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.AuthService;

@RequestMapping("/userLogin")
@RestController
public class SetCabProfileController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	CabRepository cabRepository;
	
	
	@PostMapping("/setCabDetails")
	public ResponseEntity<Object> setCabDetails(@RequestBody CabDetails cabDetails)
	{
		CabDetails fetchCab = authService.fetchCabByRegistrationNo(cabDetails.getCabRegistrationNo());
		if(fetchCab == null) {
			CabDetails cab = authService.createCab(cabDetails);

			CabDetailsResponse response = new CabDetailsResponse(new Date(), "Cab Created Succesfully", "200", cab);

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"Already Cab Exists!!","401");
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}

	
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
		
			return new ResponseEntity<Object>(fetchCab,HttpStatus.OK);
		}
	

	@PostMapping("/updateCabDetails")
	public ResponseEntity<Object> updateUser(@RequestBody UpdateCabRequest cab)
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

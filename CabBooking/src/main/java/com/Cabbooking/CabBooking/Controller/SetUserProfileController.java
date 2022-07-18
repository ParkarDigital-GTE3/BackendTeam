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
import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;
import com.Cabbooking.CabBooking.Request.UpdateCabRequest;
import com.Cabbooking.CabBooking.Request.UpdateUserRequest;
import com.Cabbooking.CabBooking.Response.CabDetailsResponse;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Response.CustomerResponseForInvalidLogin;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.AuthService;

@RequestMapping("/login")
@RestController
public class SetUserProfileController {
	
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
	
	
	//Update Customer Profile
	@PostMapping("/updateCustomerDetails")
	public ResponseEntity<Object> updateCustomerDetails(@RequestBody UpdateUserRequest customer)
		{
			Customer fetchCustomer = authService.fetchCustomerByEmail(customer.getEmail());

			if(!encoder.matches(customer.getPassword(), fetchCustomer.getPassword())) {
				CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Wrong password", new Date());
				return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
			}
			
			if(customer.getContactNumber()!="")
			{
				fetchCustomer.setContactNo(customer.getContactNumber());
			}
			
			if(customer.getName()!="")
			{
				fetchCustomer.setName(customer.getName());
			}
			if(customer.getEmail()!="")
			{
				fetchCustomer.setEmail(customer.getEmail());
			}


			customerRepository.save(fetchCustomer);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}

	
	//Update Driver Profile
	@PostMapping("/updateDriverDetails")
	public ResponseEntity<Object> updateDriverDetails(@RequestBody UpdateUserRequest driver)
		{
			CabDriver fetchDriver = authService.fetchDriverByEmail(driver.getEmail());

			if(!encoder.matches(driver.getPassword(), fetchDriver.getPassword())) {
				CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Wrong password", new Date());
				return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
			}
			
			if(driver.getContactNumber()!="")
			{
				fetchDriver.setContactNumber(driver.getContactNumber());
			}
			if(driver.getName()!="")
			{
				fetchDriver.setName(driver.getName());
			}
			if(driver.getEmail()!="")
			{
				fetchDriver.setEmail(driver.getEmail());
			}
			driverRepository.save(fetchDriver);
			CustomerResponseForInvalidLogin response = new CustomerResponseForInvalidLogin("Success", new Date());
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
	
	
	// Set Cab Profile
	@PostMapping("/setCabDetails")
	public ResponseEntity<Object> setCabDetails(@RequestBody CabDetails cabDetails)
	{
		CabDetails fetchCab = authService.fetchCabByRegistrationNo(cabDetails.getCabRegistrationNo());
		CabDriver driver = authService.fetchDriverById(cabDetails.getDriver_id().getDriver_id());
		if(fetchCab == null) {
			cabDetails.setDriver_id(driver);
			CabDetails cab = authService.createCab(cabDetails,driver);

			CabDetailsResponse response = new CabDetailsResponse(new Date(), "Cab Created Succesfully", "200", cab);

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
		
			return new ResponseEntity<Object>(fetchCab,HttpStatus.OK);
		}
	
	// Update Cab Details
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

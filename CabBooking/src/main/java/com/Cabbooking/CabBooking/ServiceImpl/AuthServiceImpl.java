package com.Cabbooking.CabBooking.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;

import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;

import com.Cabbooking.CabBooking.Service.AuthService;
import com.mysql.cj.jdbc.Driver;

@Service
public class AuthServiceImpl implements AuthService {

    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    PasswordEncoder encoder;

    //Creating  entities
    
    //creating customer
    @Override
    public Customer createCustomer(Customer customer) {
        // TODO Auto-generated method stub
    	customer.setPassword(encoder.encode(customer.getPassword()));
        return  customerRepository.save(customer);
    }

    //creating driver
    @Override
    public CabDriver createDriver(CabDriver driver) {
        // TODO Auto-generated method stub
    	driver.setPassword(encoder.encode(driver.getPassword()));
        return  driverRepository.save(driver);
    }

    //creating cab
	@Override
	public CabDetails createCab(CabDetails cabDetails) {
		
		return cabRepository.save(cabDetails);
	}
	
	//Fetching the details
	
	//fetching customer
    @Override
    public Customer fetchCustomerByEmail(String emailId) {
        // TODO Auto-generated method stub
        return customerRepository.fetchCustomerByEmail(emailId);
    }

    //fetching customer
    @Override
    public CabDriver fetchDriverByEmail(String emailId) {
        // TODO Auto-generated method stub
        return driverRepository.fetchDriverByEmail(emailId);
    }

    //fetching customer
	@Override
	public CabDetails fetchCabByRegistrationNo(String cabRegistrationNo) {
		
        return cabRepository.fetchCabByRegistrationNo(cabRegistrationNo);
		// TODO Auto-generated method stub

	}


	//Updating Password
	//updating customer password
	@Override
	public void updateCustomerPassword(String emailId, String newPassword) {
		Customer cust = customerRepository.fetchCustomerByEmail(emailId);
		cust.setPassword(encoder.encode(newPassword));
		customerRepository.save(cust);		
	}

	//updating driver password
	@Override
	public void updateDriverPassword(String emailId, String newPassword) {
		CabDriver driver  = driverRepository.fetchDriverByEmail(emailId);
		driver.setPassword(encoder.encode(newPassword));
		driverRepository.save(driver);
	}

	@Override
	public CabDriver fetchDriverById(long driver_id) {
		// TODO Auto-generated method stub
		return driverRepository.fetchDriverById(driver_id);
	}

	@Override
	public CabDriver updateDriverCabId(CabDriver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public CabDetails fetchCabById(long cab_id) {
		return cabRepository.fetchCabById(cab_id);
	}



}

package com.AspasCabCustomer.Customer.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspasCabCustomer.Customer.Model.CabDriver;
import com.AspasCabCustomer.Customer.Model.Customer;
import com.AspasCabCustomer.Customer.Model.RatesAndTypes;
import com.AspasCabCustomer.Customer.Repository.CarTypesAndRatesRepo;
import com.AspasCabCustomer.Customer.Repository.CustomerRepository;
import com.AspasCabCustomer.Customer.Repository.DriverRepository;
import com.AspasCabCustomer.Customer.Repository.LocationRepository;
import com.AspasCabCustomer.Customer.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	CarTypesAndRatesRepo carTypesAndRatesRepo;
	
	@Autowired
	DriverRepository driverRepository;

	
	@Override
	public Customer updateCustomer(Customer fetchCustomer) {
		return customerRepository.save(fetchCustomer);
	}

	@Override
	public Customer getById(long customerId) {

		return customerRepository.getCustomerById(customerId);
	}


	@Override
	public Customer fetchCustomerByEmail(String customerEmail) {
		// TODO Auto-generated method stub
		return  customerRepository.fetchCustomerByEmail(customerEmail);
	}

	@Override
	public List<String> getSource() {
		return locationRepository.getSource();
	}

	@Override
	public List<String> getDestination() {
		
		return locationRepository.getDestination();
	}

	@Override
	public List<String> getCabTypes() {

		return carTypesAndRatesRepo.getCabTypes();
	}

	@Override
	public RatesAndTypes fetchRatesAndCapacity(String cabType) {
	
		return carTypesAndRatesRepo.fetchRatesAndCapacity(cabType);
	}

	@Override
	public Long fetchTotalDistanceByLocations(String source, String destination) {
		return locationRepository.fetchTotalDistance(source, destination);
	}



	@Override
	public CabDriver getDriverById(Long driverId) {
		// TODO Auto-generated method stub
		return driverRepository.getDriverById(driverId);
	}

	@Override
	public Long fetchRates(String cabType) {
		
		return carTypesAndRatesRepo.getRateByCabType(cabType);
	}

	@Override
	public String fetchCapcity(String cabType) {
		// TODO Auto-generated method stub
		return carTypesAndRatesRepo.getCapcityByCabType(cabType);
	}

	
}

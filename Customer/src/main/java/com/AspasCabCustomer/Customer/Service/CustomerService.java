package com.AspasCabCustomer.Customer.Service;

import java.util.List;

import com.AspasCabCustomer.Customer.Model.CabDriver;
import com.AspasCabCustomer.Customer.Model.Customer;
import com.AspasCabCustomer.Customer.Model.RatesAndTypes;



public interface CustomerService {


	Customer updateCustomer(Customer fetchCustomer);

	Customer getById(long customerId);

	Customer fetchCustomerByEmail(String customerEmail);

	List<String> getSource();

	List<String> getDestination();

	List<String> getCabTypes();

	Long fetchTotalDistanceByLocations(String source, String destination);

	RatesAndTypes fetchRatesAndCapacity(String cabType);

	CabDriver getDriverById(Long driverId);

	Long fetchRates(String cabType);

	String fetchCapcity(String cabType);

}

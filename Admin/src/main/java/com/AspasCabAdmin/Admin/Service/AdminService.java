package com.AspasCabAdmin.Admin.Service;

import java.util.List;

import com.AspasCabAdmin.Admin.Model.CabDetails;
import com.AspasCabAdmin.Admin.Model.CabDriver;
import com.AspasCabAdmin.Admin.Model.Customer;
import com.AspasCabAdmin.Admin.Model.Location;
import com.AspasCabAdmin.Admin.Model.RatesAndTypes;
import com.AspasCabAdmin.Admin.Model.TripDetails;

public interface AdminService {

	// Deriver Activation
	CabDriver fetchDriverByEmail(String email);

	List<CabDriver> findAllByActivationStatus();

	CabDriver updateDriverRegistration(CabDriver driver);
	
	//Set Locations and cab rates
	Location setLocation(Location location);

	RatesAndTypes addCarType(RatesAndTypes cabTypes);

	//Dashboard Service
	Long countDriver();

	Long countCustomer();

	Long fetchTotalEarning();

	List<String> countTripByDate();

	List<String> CountTripByLocation();

	Long fetchTodaysTotalEarning(String date);

	List<Customer> fetchAllCustomers();

	List<CabDriver> fetchAllDrivers();

	List<CabDetails> findAllCabs();

	List<TripDetails> findAllTrips();

	TripDetails getTripById(long id);

	Location fetchLocationBySrcAndDest(String source, String destination);

	RatesAndTypes fetchCabBytypeAndCapacity(String type, String capacity);

	List<Location> fetchAllLocations();

	List<RatesAndTypes> fetchAllTypes();

	CabDriver fetchDriverById(long driver_id);

}

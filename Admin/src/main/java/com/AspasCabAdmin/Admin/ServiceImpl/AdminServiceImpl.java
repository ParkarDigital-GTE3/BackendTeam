package com.AspasCabAdmin.Admin.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspasCabAdmin.Admin.Model.CabDetails;
import com.AspasCabAdmin.Admin.Model.CabDriver;
import com.AspasCabAdmin.Admin.Model.Customer;
import com.AspasCabAdmin.Admin.Model.Location;
import com.AspasCabAdmin.Admin.Model.RatesAndTypes;
import com.AspasCabAdmin.Admin.Model.TripDetails;
import com.AspasCabAdmin.Admin.Repository.CabRepository;
import com.AspasCabAdmin.Admin.Repository.CarTypesAndRatesRepo;
import com.AspasCabAdmin.Admin.Repository.CustomerRepository;
import com.AspasCabAdmin.Admin.Repository.DriverRepository;
import com.AspasCabAdmin.Admin.Repository.LocationRepository;
import com.AspasCabAdmin.Admin.Repository.TripRepository;
import com.AspasCabAdmin.Admin.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	TripRepository tripRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	CarTypesAndRatesRepo carTypesAndRatesRepo;
	
	@Override
	public CabDriver fetchDriverByEmail(String email) {
		return driverRepository.fetchDriverByEmail(email);
	}
	
	@Override
	public List<CabDriver> findAllByActivationStatus() {
		return driverRepository.findAllByActivationStatus();
	}

	@Override
	public CabDriver updateDriverRegistration(CabDriver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public Long countDriver() {
		
		return driverRepository.count();
	}

	@Override
	public Long countCustomer() {

		return customerRepository.count();
	}

	@Override
	public Long fetchTotalEarning() {
		
		return tripRepository.fetchTotalEarning();
	}

	@Override
	public Long fetchTodaysTotalEarning(String date) {

		return tripRepository.fetchTodaysTotalEarning(date);
	}
	
	@Override
	public List<String> countTripByDate() {
		
		return tripRepository.countTripByDate();
	}

	@Override
	public List<String> CountTripByLocation() {

		return tripRepository.CountTripByLocation();
	}

	@Override
	public Location setLocation(Location location) {

		return locationRepository.save(location);
	}

	@Override
	public RatesAndTypes addCarType(RatesAndTypes cabTypes) {

		return carTypesAndRatesRepo.save(cabTypes);
	}

	@Override
	public List<Customer> fetchAllCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public List<CabDriver> fetchAllDrivers() {

		return driverRepository.findAll();
	}

	@Override
	public List<CabDetails> findAllCabs() {

		return cabRepository.findAll();
	}

	@Override
	public List<TripDetails> findAllTrips() {
		return tripRepository.findAll();
	}

	@Override
	public TripDetails getTripById(long id) {
		// TODO Auto-generated method stub
		return tripRepository.getTripById(id);
	}

	@Override
	public Location fetchLocationBySrcAndDest(String source, String destination) {
		
		return locationRepository.fetchLocationBySrcAndDest(source, destination);
	}

	@Override
	public RatesAndTypes fetchCabBytypeAndCapacity(String type, String capacity) {
		// TODO Auto-generated method stub
		return carTypesAndRatesRepo.fetchCabBytypeAndCapacity(type,capacity);
	}

	@Override
	public List<Location> fetchAllLocations() {
	
		return locationRepository.findAll();
	}

	@Override
	public List<RatesAndTypes> fetchAllTypes() {
		
		return carTypesAndRatesRepo.findAll();
	}

	@Override
	public CabDriver fetchDriverById(long driver_id) {
		
		return driverRepository.getById(driver_id);
	}


}

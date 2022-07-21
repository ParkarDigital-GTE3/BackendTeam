package com.Cabbooking.CabBooking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.Admin;
import com.Cabbooking.CabBooking.Model.Location;
import com.Cabbooking.CabBooking.Model.RatesAndTypes;
import com.Cabbooking.CabBooking.Repository.AdminRepository;
import com.Cabbooking.CabBooking.Repository.CarTypesAndRatesRepo;
import com.Cabbooking.CabBooking.Repository.LocationRepository;
import com.Cabbooking.CabBooking.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	CarTypesAndRatesRepo carTypesAndRatesRepo;
	
	@Autowired
	AdminRepository adminRepository;
	
	
	@Override
	public Location setLocation(Location location) {

		return locationRepository.save(location);
	}

	@Override
	public RatesAndTypes addCarType(RatesAndTypes cabTypes) {

		return carTypesAndRatesRepo.save(cabTypes);
	}

	@Override
	public Admin getByUsername(String userName) {
		return adminRepository.getByUsername(userName);
	}

	@Override
	public Long fetchTotalDistanceByLocations(String source, String destination) {
		return locationRepository.fetchTotalDistance(source, destination);
	}

	@Override
	public long fetchRatesPerKmByCab(String cabType, String cabCapacity) {
		
		return carTypesAndRatesRepo.fetchRates(cabType, cabCapacity);
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



}

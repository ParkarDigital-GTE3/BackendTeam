package com.AspasCabDriver.Driver.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspasCabDriver.Driver.Model.TripDetails;
import com.AspasCabDriver.Driver.Repository.TripRepository;
import com.AspasCabDriver.Driver.Service.TripService;



@Service
public class TripServiceImpl implements TripService {

	@Autowired
	TripRepository tripRepository;
	@Override
	public TripDetails tripDetails(TripDetails details) {
		
		return tripRepository.save(details);
	}
	
	@Override
	public TripDetails updateTrip(TripDetails trip) {
		
		return  tripRepository.save(trip);
	}
	
	@Override
	public List<TripDetails> findAllTrips() {
	
		return tripRepository.findAll();
	}


	@Override
	public TripDetails createTrip(TripDetails trip) {
		
		return tripRepository.save(trip);
	}

	@Override
	public List<TripDetails> fetchTripByDriverEmail(String email) {
		
		return tripRepository.fetchTripByDriverEmail(email);
	}

	@Override
	public long calculateTotalEarning(String email) {

		return tripRepository.calculateTotalEarning(email);
	}

	@Override
	public List<TripDetails> fetchTripByCustomerEmail(String email) {
	
		return tripRepository.fetchTripByCustomerEmail(email);
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
	public TripDetails getTripById(long trip_id) {
		
		return tripRepository.getTripById(trip_id);
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
	public TripDetails getTripByBookingId(long booking_id) {
		// TODO Auto-generated method stub
		return tripRepository.getTripByBookingId(booking_id);
	}
	

}

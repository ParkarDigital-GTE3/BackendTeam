package com.Cabbooking.CabBooking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.TripDetails;
import com.Cabbooking.CabBooking.Repository.TripRepository;
import com.Cabbooking.CabBooking.Request.TripDateWiseRequest;
import com.Cabbooking.CabBooking.Response.LocationWiseTrips;
import com.Cabbooking.CabBooking.Service.TripService;


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
	public List<TripDateWiseRequest> countTripByDate() {
		
		return tripRepository.countTripByDate();
	}

	@Override
	public List<LocationWiseTrips> CountTripByLocation() {

		return tripRepository.CountTripByLocation();
	}
	

}

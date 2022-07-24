package com.AspasCabCustomer.Customer.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspasCabCustomer.Customer.Model.TripDetails;
import com.AspasCabCustomer.Customer.Repository.TripRepository;
import com.AspasCabCustomer.Customer.Service.TripService;



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
	public TripDetails createTrip(TripDetails trip) {
		
		return tripRepository.save(trip);
	}



	@Override
	public List<TripDetails> fetchTripByCustomerEmail(String email) {
	
		return tripRepository.fetchTripByCustomerEmail(email);
	}


	@Override
	public TripDetails getTripById(long trip_id) {
		
		return tripRepository.getTripById(trip_id);
	}



	@Override
	public TripDetails getTripByBookingId(long booking_id) {
		// TODO Auto-generated method stub
		return tripRepository.getTripByBookingId(booking_id);
	}
	

}

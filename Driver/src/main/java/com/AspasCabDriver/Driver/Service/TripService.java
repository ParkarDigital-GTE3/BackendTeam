package com.AspasCabDriver.Driver.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AspasCabDriver.Driver.Model.TripDetails;


public interface TripService {

	TripDetails tripDetails(TripDetails details);

	TripDetails updateTrip(TripDetails trip);

	List<TripDetails> findAllTrips();


	TripDetails createTrip(TripDetails trip);

	List<TripDetails> fetchTripByDriverEmail(String email);

	long calculateTotalEarning(String email);

	List<TripDetails> fetchTripByCustomerEmail(String email);

	Long fetchTotalEarning();

	Long fetchTodaysTotalEarning(String date);


	TripDetails getTripById(long trip_id);

	List<String> countTripByDate();

	List<String> CountTripByLocation();

	TripDetails getTripByBookingId(long booking_id);


}

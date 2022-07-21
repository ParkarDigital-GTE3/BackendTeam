package com.Cabbooking.CabBooking.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.TripDetails;
import com.Cabbooking.CabBooking.Request.TripDateWiseRequest;
import com.Cabbooking.CabBooking.Response.LocationWiseTrips;

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

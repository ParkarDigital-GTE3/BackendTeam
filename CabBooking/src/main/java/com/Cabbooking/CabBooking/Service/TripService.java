package com.Cabbooking.CabBooking.Service;

import java.util.List;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.TripDetails;

public interface TripService {

	TripDetails tripDetails(TripDetails details);

	TripDetails updateTrip(TripDetails trip);

	List<TripDetails> findAllTrips();

	TripDetails getSpecificTrip(long id);

	TripDetails getById(long id);

	TripDetails createTrip(TripDetails trip);

	List<TripDetails> fetchTripByDriverEmail(String email);

	long calculateTotalEarning(String email);


}

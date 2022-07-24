package com.AspasCabCustomer.Customer.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AspasCabCustomer.Customer.Model.TripDetails;



public interface TripService {

	TripDetails tripDetails(TripDetails details);

	TripDetails updateTrip(TripDetails trip);



	TripDetails createTrip(TripDetails trip);


	List<TripDetails> fetchTripByCustomerEmail(String email);


	TripDetails getTripById(long trip_id);


	TripDetails getTripByBookingId(long booking_id);


}

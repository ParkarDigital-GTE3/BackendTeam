package com.Cabbooking.CabBooking.Service;

import com.Cabbooking.CabBooking.Model.TripDetails;

public interface TripService {

	TripDetails tripDetails(TripDetails details);

	TripDetails updateTrip(TripDetails trip);


}

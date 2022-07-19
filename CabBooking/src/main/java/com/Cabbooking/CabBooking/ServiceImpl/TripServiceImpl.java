package com.Cabbooking.CabBooking.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.TripDetails;
import com.Cabbooking.CabBooking.Repository.TripRepository;
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
	

}

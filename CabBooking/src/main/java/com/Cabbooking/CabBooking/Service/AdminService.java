package com.Cabbooking.CabBooking.Service;

import java.util.List;

import com.Cabbooking.CabBooking.Model.Admin;
import com.Cabbooking.CabBooking.Model.Location;
import com.Cabbooking.CabBooking.Model.RatesAndTypes;

public interface AdminService {


	Location setLocation(Location location);

	RatesAndTypes addCarType(RatesAndTypes cabTypes);

	Admin getByUsername(String userName);

	Long fetchTotalDistanceByLocations(String source, String destination);

	long fetchRatesPerKmByCab(String cabType, String cabCapacity);

	List<String> getSource();

	List<String> getDestination();

	List<String> getCabTypes();

	RatesAndTypes fetchRatesAndCapacity(String cabType);



}

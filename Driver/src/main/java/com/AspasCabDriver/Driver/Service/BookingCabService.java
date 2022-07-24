package com.AspasCabDriver.Driver.Service;

import java.util.List;
import java.util.Optional;

import com.AspasCabDriver.Driver.Model.Booking;



public interface BookingCabService {

	Booking bookCab(Booking bookingDetails);

	Booking updateBooking(long id);

	List<Booking> findBookingByStatus();

	Optional<Booking> findBookingById(long id);

	Booking getBookingById(long id);

	Long fetchTotalDistanceByLocations(String source, String destination);

	Long fetchRatesPerKmByCab(String cabType, String cabCapacity);
}

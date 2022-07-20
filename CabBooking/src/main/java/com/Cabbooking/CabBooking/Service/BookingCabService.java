package com.Cabbooking.CabBooking.Service;

import java.util.List;
import java.util.Optional;

import com.Cabbooking.CabBooking.Model.Booking;

public interface BookingCabService {

	Booking bookCab(Booking bookingDetails);

	Booking updateBooking(long id);

	List<Booking> findBookingByStatus();

	Optional<Booking> findBookingById(long id);

	Booking getBookingById(long id);
}

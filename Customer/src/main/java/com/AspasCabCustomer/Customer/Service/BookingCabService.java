package com.AspasCabCustomer.Customer.Service;

import java.util.List;
import java.util.Optional;

import com.AspasCabCustomer.Customer.Model.Booking;


public interface BookingCabService {

	Booking bookCab(Booking bookingDetails);

	Booking updateBooking(long id);

	List<Booking> findBookingByStatus();

	Optional<Booking> findBookingById(long id);

	Booking getBookingById(long id);
}

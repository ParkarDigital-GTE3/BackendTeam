package com.Cabbooking.CabBooking.Service;

import com.Cabbooking.CabBooking.Model.Booking;

public interface BookingCabService {

	Booking bookCab(Booking bookingDetails);

	Booking updateBooking(long id);
}

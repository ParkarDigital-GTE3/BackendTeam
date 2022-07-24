package com.AspasCabCustomer.Customer.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspasCabCustomer.Customer.Model.Booking;
import com.AspasCabCustomer.Customer.Repository.BookingRepository;
import com.AspasCabCustomer.Customer.Service.BookingCabService;



@Service
public class BookingCabServiceImpl implements BookingCabService {
	
	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Booking bookCab(Booking bookingDetails) {
		
		return bookingRepository.save(bookingDetails);
	}

	@Override
	public Booking updateBooking(long id) {
		Booking booking = bookingRepository.getById(id);
		booking.setStatus("1");
		return	bookingRepository.save(booking);

	}

	@Override
	public List<Booking> findBookingByStatus() {

		return bookingRepository.findAll();
	}

	@Override
	public Optional<Booking> findBookingById(long id) {
		return bookingRepository.findById(id);
	}

	@Override
	public Booking getBookingById(long id) {
		return bookingRepository.getBookingById(id);
	}


}

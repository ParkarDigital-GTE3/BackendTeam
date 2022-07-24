package com.AspasCabDriver.Driver.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspasCabDriver.Driver.Model.Booking;
import com.AspasCabDriver.Driver.Repository.BookingRepository;
import com.AspasCabDriver.Driver.Repository.CarTypesAndRatesRepo;
import com.AspasCabDriver.Driver.Repository.LocationRepository;
import com.AspasCabDriver.Driver.Service.BookingCabService;


@Service
public class BookingCabServiceImpl implements BookingCabService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	CarTypesAndRatesRepo carTypesAndRatesRepo;

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

	@Override
	public Long fetchTotalDistanceByLocations(String source, String destination) {
		return locationRepository.fetchTotalDistance(source, destination);
	}

	@Override
	public Long fetchRatesPerKmByCab(String cabType, String cabCapacity) {
		
		return carTypesAndRatesRepo.fetchRates(cabType, cabCapacity);
	}

}

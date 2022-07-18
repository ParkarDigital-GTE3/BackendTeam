package com.Cabbooking.CabBooking.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Repository.BookingRepository;
import com.Cabbooking.CabBooking.Response.BookingResponse;
import com.Cabbooking.CabBooking.Response.UserResponseForNoUser;
import com.Cabbooking.CabBooking.Service.BookingCabService;

@RequestMapping("/login")
@RestController
public class BookCabController {
	
	@Autowired
	BookingCabService bookingCabService;
	
	@Autowired
	BookingRepository bookingRepository;
	
	// Create Booking Customer
	@PostMapping("/bookCab")
	public ResponseEntity<Object> bookCab(@RequestBody Booking bookingDetails){
		
		if(bookingDetails == null) {
		UserResponseForNoUser response = new UserResponseForNoUser(new Date(),"No Booking Details","409");
		return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
		Booking result = bookingCabService.bookCab(bookingDetails);
		BookingResponse response = new BookingResponse(new Date(),"Booking Confirmed","200",result);
		return new ResponseEntity<Object>(response,HttpStatus.CREATED);
	}

	//Get Bookings Driver
	@GetMapping("/getBookings")
	public ResponseEntity<Object> getBookings(){
		List<Booking> response = bookingRepository.findAll();
		return new ResponseEntity<Object>(response,HttpStatus.FOUND);
	}
	
	//get single Booking by id
	@GetMapping("/getBooking/{id}")
	public ResponseEntity<Object> getBookings(@PathVariable("id") long id){
		Optional<Booking> response = bookingRepository.findById(id);
		return new ResponseEntity<Object>(response,HttpStatus.FOUND);
	}
	
	
	//update booking status as closed
	@PatchMapping("/acceptBooking/{id}")
	public ResponseEntity<Object> updateBookingStatus(@PathVariable("id") long id){
		Booking fetchBooking = bookingRepository.getById(id);
		
		if(fetchBooking==null) {
			UserResponseForNoUser response =  new UserResponseForNoUser(new Date(),"Booking does not Exists!!","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
		Booking response = bookingCabService.updateBooking(id);
		return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
	}
	
	
	
}

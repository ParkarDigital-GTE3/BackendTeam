package com.AspasCabDriver.Driver.Response;

import java.util.List;

import com.AspasCabDriver.Driver.Model.Booking;

public class CustomBookingResponse {

	private String status;
	private List<Booking> bookings;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public CustomBookingResponse(String status, List<Booking> bookings) {
		super();
		this.status = status;
		this.bookings = bookings;
	}
	
}

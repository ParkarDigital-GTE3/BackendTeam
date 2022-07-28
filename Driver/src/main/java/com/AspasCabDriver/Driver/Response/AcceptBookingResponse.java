package com.AspasCabDriver.Driver.Response;

import com.AspasCabDriver.Driver.Model.Booking;

public class AcceptBookingResponse {

	private String status;
	private Booking booking;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public AcceptBookingResponse(String status, Booking booking) {
		super();
		this.status = status;
		this.booking = booking;
	}
	
}

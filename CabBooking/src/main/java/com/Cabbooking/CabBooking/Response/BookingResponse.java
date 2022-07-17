package com.Cabbooking.CabBooking.Response;

import java.util.Date;

import com.Cabbooking.CabBooking.Model.Booking;

public class BookingResponse {

	 private Date timestamp;
	 private String message;
	 private String status;
	 private Booking booking;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	@Override
	public String toString() {
		return "BookingResponse [timestamp=" + timestamp + ", message=" + message + ", status=" + status + ", booking="
				+ booking + "]";
	}
	public BookingResponse(Date timestamp, String message, String status, Booking booking) {
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.booking = booking;
	}
	public BookingResponse() {
		// TODO Auto-generated constructor stub
	}
	 
	 
}

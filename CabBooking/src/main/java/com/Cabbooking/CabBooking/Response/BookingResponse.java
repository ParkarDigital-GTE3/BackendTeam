package com.Cabbooking.CabBooking.Response;

import java.util.Date;

import com.Cabbooking.CabBooking.Model.Booking;

public class BookingResponse {

	 private Date timestamp;
	 private String message;
	 private String status;
	 private Booking booking;
	 private String customerName;
	 private String customerContactNo;
	
	public BookingResponse(Date timestamp, String message, String status, Booking booking, String customerName,
			String customerContactNo) {
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.booking = booking;
		this.customerName = customerName;
		this.customerContactNo = customerContactNo;
	}

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContactNo() {
		return customerContactNo;
	}

	public void setCustomerContactNo(String customerContactNo) {
		this.customerContactNo = customerContactNo;
	}

	public BookingResponse() {
		// TODO Auto-generated constructor stub
	}
	 
	 
}

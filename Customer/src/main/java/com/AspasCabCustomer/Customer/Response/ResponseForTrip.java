package com.AspasCabCustomer.Customer.Response;

import java.util.Date;

import com.AspasCabCustomer.Customer.Model.TripDetails;

public class ResponseForTrip {

	private Date date;
	private String message;
	private String status;
	private TripDetails trip;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public TripDetails getTrip() {
		return trip;
	}
	public void setTrip(TripDetails trip) {
		this.trip = trip;
	}
	public ResponseForTrip(Date date, String message, String status, TripDetails trip) {
		super();
		this.date = date;
		this.message = message;
		this.status = status;
		this.trip = trip;
	}
	
	
}

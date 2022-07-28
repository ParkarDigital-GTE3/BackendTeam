package com.AspasCabCustomer.Customer.Response;

import java.util.List;

import com.AspasCabCustomer.Customer.Model.TripDetails;

public class CustomHistoryResponse {

	private String status;
	private List<TripDetails> trips;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TripDetails> getTrips() {
		return trips;
	}
	public void setTrips(List<TripDetails> trips) {
		this.trips = trips;
	}
	public CustomHistoryResponse(String status, List<TripDetails> trips) {
		super();
		this.status = status;
		this.trips = trips;
	}
	
}

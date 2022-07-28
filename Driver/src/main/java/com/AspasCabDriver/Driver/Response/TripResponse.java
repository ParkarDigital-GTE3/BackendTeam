package com.AspasCabDriver.Driver.Response;

import com.AspasCabDriver.Driver.Model.TripDetails;

public class TripResponse {

	private String status;
	private TripDetails trip;
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
	public TripResponse(String status, TripDetails trip) {
		super();
		this.status = status;
		this.trip = trip;
	}
	
}

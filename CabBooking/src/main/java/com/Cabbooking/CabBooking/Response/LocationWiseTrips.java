package com.Cabbooking.CabBooking.Response;

public class LocationWiseTrips {

	private String source;
	private String destination;
	private long trips;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getTrips() {
		return trips;
	}
	public void setTrips(long trips) {
		this.trips = trips;
	}
	public LocationWiseTrips(String source, String destination, long trips) {
		this.source = source;
		this.destination = destination;
		this.trips = trips;
	}
	public LocationWiseTrips() {
		// TODO Auto-generated constructor stub
	}
	
}

package com.Cabbooking.CabBooking.Request;

public class TripDateWiseRequest {

	private String date;
	private long count;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public TripDateWiseRequest(String date, long count) {
		this.date = date;
		this.count = count;
	}
	public TripDateWiseRequest() {
		// TODO Auto-generated constructor stub
	}
	
}

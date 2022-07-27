package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

import com.AspasCabAdmin.Admin.Model.TripDetails;

public class TripHistoryDetailsResponse {
	private Date date;
	private String msg;
	private String status;
	private List<TripDetails> trips;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
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
	public TripHistoryDetailsResponse(Date date, String msg, String status, List<TripDetails> trips) {
		this.date = date;
		this.msg = msg;
		this.status = status;
		this.trips = trips;
	}
	public TripHistoryDetailsResponse() {
		// TODO Auto-generated constructor stub
	}

	
}

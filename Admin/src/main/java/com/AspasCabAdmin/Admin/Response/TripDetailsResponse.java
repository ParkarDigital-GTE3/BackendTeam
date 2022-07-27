package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

import com.AspasCabAdmin.Admin.Model.TripDetails;


public class TripDetailsResponse {
	private Date date;
	private String msg;
	private String status;
	private TripDetails trip;
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
	public TripDetails getTrip() {
		return trip;
	}
	public void setTrip(TripDetails trip) {
		this.trip = trip;
	}
	public TripDetailsResponse(Date date, String msg, String status, TripDetails trip) {
		this.date = date;
		this.msg = msg;
		this.status = status;
		this.trip = trip;
	}
	public TripDetailsResponse() {
		// TODO Auto-generated constructor stub
	}
	

}

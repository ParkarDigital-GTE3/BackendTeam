package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

import com.AspasCabAdmin.Admin.Model.CabDriver;


public class DriverDetailsResponse {

	private Date date;
	private String msg;
	private String status;
	private List<CabDriver> drivers;
	public DriverDetailsResponse() {
		// TODO Auto-generated constructor stub
	}
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
	public List<CabDriver> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<CabDriver> drivers) {
		this.drivers = drivers;
	}
	public DriverDetailsResponse(Date date, String msg, String status, List<CabDriver> drivers) {
		this.date = date;
		this.msg = msg;
		this.status = status;
		this.drivers = drivers;
	}
	
}

package com.AspasCabDriver.Driver.Response;

import java.util.Date;

import com.AspasCabDriver.Driver.Model.CabDriver;



public class CabDetailsResponse {
	 private Date timestamp;
	 private String message;
	 private String status;
	 private CabDriver result;
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
	public CabDriver getResult() {
		return result;
	}
	public void setResult(CabDriver result) {
		this.result = result;
	}
	public CabDetailsResponse(Date timestamp, String message, String status, CabDriver result) {
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.result = result;
	}
	public CabDetailsResponse() {
		// TODO Auto-generated constructor stub
	}
	

}

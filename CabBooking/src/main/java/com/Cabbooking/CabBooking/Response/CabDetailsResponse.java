package com.Cabbooking.CabBooking.Response;

import java.util.Date;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.User;

public class CabDetailsResponse {
	 private Date timestamp;
	 private String message;
	 private String status;
	 private CabDetails result;
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
	public CabDetails getResult() {
		return result;
	}
	public void setResult(CabDetails result) {
		this.result = result;
	}
	public CabDetailsResponse(Date timestamp, String message, String status, CabDetails result) {
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.result = result;
	}
	 
	 

}

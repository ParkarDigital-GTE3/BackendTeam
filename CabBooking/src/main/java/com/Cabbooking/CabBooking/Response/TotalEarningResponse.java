package com.Cabbooking.CabBooking.Response;

public class TotalEarningResponse {
	private String message;
	private String status;
	private long result;
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
	public long getResult() {
		return result;
	}
	public void setResult(long result) {
		this.result = result;
	}
	public TotalEarningResponse(String message, String status, long result) {
		this.message = message;
		this.status = status;
		this.result = result;
	}
	public TotalEarningResponse() {
		// TODO Auto-generated constructor stub
	}
	

}

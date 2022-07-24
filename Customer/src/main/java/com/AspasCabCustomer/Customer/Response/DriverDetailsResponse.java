package com.AspasCabCustomer.Customer.Response;

import java.util.Date;

import com.AspasCabCustomer.Customer.Model.CabDriver;



public class DriverDetailsResponse {
	
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
		public DriverDetailsResponse() {
			// TODO Auto-generated constructor stub
		}
		public DriverDetailsResponse(Date timestamp, String message, String status, CabDriver result) {
			this.timestamp = timestamp;
			this.message = message;
			this.status = status;
			this.result = result;
		}
		@Override
		public String toString() {
			return "DriverDetailsResponse [timestamp=" + timestamp + ", message=" + message + ", status=" + status
					+ ", result=" + result + "]";
		}
	    


}

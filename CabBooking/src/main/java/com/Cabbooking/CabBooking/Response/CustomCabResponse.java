package com.Cabbooking.CabBooking.Response;

import java.util.Date;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;

public class CustomCabResponse {
	

	 	private Date timestamp;
	    private String message;
	    private String status;
	    private CabDriver driver;
	    private CabDetails cab;
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
		public CabDriver getDriver() {
			return driver;
		}
		public void setDriver(CabDriver driver) {
			this.driver = driver;
		}
		public CabDetails getCab() {
			return cab;
		}
		public void setCab(CabDetails cab) {
			this.cab = cab;
		}
		public CustomCabResponse() {
			// TODO Auto-generated constructor stub
		}
		public CustomCabResponse(Date timestamp, String message, String status, CabDriver driver, CabDetails cab) {
			this.timestamp = timestamp;
			this.message = message;
			this.status = status;
			this.driver = driver;
			this.cab = cab;
		}
		@Override
		public String toString() {
			return "CustomCabResponse [timestamp=" + timestamp + ", message=" + message + ", status=" + status
					+ ", driver=" + driver + ", cab=" + cab + "]";
		}
	    

}

package com.AspasCabAdmin.Admin.Response;

import java.util.Date;

import com.AspasCabAdmin.Admin.Model.Admin;

public class AdminDetailsResponse {

	private Date timestamp;
	private String message;
	private String status;
	private Admin admin;
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
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public AdminDetailsResponse(Date timestamp, String message, String status, Admin admin) {
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.admin = admin;
	}
	
}

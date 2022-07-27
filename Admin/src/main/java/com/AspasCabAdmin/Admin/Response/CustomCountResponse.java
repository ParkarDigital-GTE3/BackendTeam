package com.AspasCabAdmin.Admin.Response;

import java.util.Date;

public class CustomCountResponse {
	private Date date;
	private String status;
	private long count;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public CustomCountResponse(Date date, String status, long count) {
		this.date = date;
		this.status = status;
		this.count = count;
	}

}

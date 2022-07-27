package com.AspasCabAdmin.Admin.Response;

import java.util.List;

import com.AspasCabAdmin.Admin.Model.Location;

public class CustomLocationResponse {
	private String msg;
	private String status;
	private List<Location> loc;
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
	public List<Location> getLoc() {
		return loc;
	}
	public void setLoc(List<Location> loc) {
		this.loc = loc;
	}
	public CustomLocationResponse(String msg, String status, List<Location> loc) {
		this.msg = msg;
		this.status = status;
		this.loc = loc;
	}
	public CustomLocationResponse() {
		// TODO Auto-generated constructor stub
	}
	

}

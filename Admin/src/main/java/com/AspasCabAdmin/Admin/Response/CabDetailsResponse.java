package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

import com.AspasCabAdmin.Admin.Model.CabDetails;


public class CabDetailsResponse {
	private Date date;
	private String msg;
	private String status;
	private List<CabDetails> cabs;
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
	public List<CabDetails> getCabs() {
		return cabs;
	}
	public void setCabs(List<CabDetails> cabs) {
		this.cabs = cabs;
	}
	public CabDetailsResponse(Date date, String msg, String status, List<CabDetails> cabs) {
		this.date = date;
		this.msg = msg;
		this.status = status;
		this.cabs = cabs;
	}
	public CabDetailsResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
}

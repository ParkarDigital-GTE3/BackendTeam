package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

import com.AspasCabAdmin.Admin.Model.Customer;

public class CustomerDetailsResponse {
	private Date date;
	private String msg;
	private String status;
	private List<Customer> customers;
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
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public CustomerDetailsResponse(Date date, String msg, String status, List<Customer> customers) {
		this.date = date;
		this.msg = msg;
		this.status = status;
		this.customers = customers;
	}
	public CustomerDetailsResponse() {
		// TODO Auto-generated constructor stub
	}
	

}

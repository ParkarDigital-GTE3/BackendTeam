package com.AspasCabAdmin.Admin.Response;

import java.util.Date;
import java.util.List;

import com.AspasCabAdmin.Admin.Model.CabDriver;
import com.AspasCabAdmin.Admin.Model.RatesAndTypes;

public class CustomTypeResponse {

	private String msg;
	private String status;
	private List<RatesAndTypes> ratesAndTypes;
	
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
	public List<RatesAndTypes> getRatesAndTypes() {
		return ratesAndTypes;
	}
	public void setRatesAndTypes(List<RatesAndTypes> ratesAndTypes) {
		this.ratesAndTypes = ratesAndTypes;
	}
	public CustomTypeResponse(String msg, String status, List<RatesAndTypes> ratesAndTypes) {

		this.msg = msg;
		this.status = status;
		this.ratesAndTypes = ratesAndTypes;
	}
	

}

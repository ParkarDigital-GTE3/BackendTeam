package com.AspasCabCustomer.Customer.Request;


public class SrcDestTypes {

	private String source;
	private String destination;
	private String cabType;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}
	public SrcDestTypes(String source, String destination, String cabType) {
		this.source = source;
		this.destination = destination;
		this.cabType = cabType;
	}
	public SrcDestTypes() {
		// TODO Auto-generated constructor stub
	}
	
}

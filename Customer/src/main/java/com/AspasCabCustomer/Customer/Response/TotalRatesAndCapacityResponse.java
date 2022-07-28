package com.AspasCabCustomer.Customer.Response;

public class TotalRatesAndCapacityResponse {
	private String status;
	private String capacity;
	private long totalRate;
	public String getCapacity() {
		return capacity;
	}
	public TotalRatesAndCapacityResponse(String status, String capacity, long totalRate) {
		super();
		this.status = status;
		this.capacity = capacity;
		this.totalRate = totalRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public long getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
	
	 
	
}

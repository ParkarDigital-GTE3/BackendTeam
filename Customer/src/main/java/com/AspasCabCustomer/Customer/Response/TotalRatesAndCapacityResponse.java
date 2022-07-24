package com.AspasCabCustomer.Customer.Response;

public class TotalRatesAndCapacityResponse {

	private String capacity;
	private long totalRate;
	public String getCapacity() {
		return capacity;
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
	public TotalRatesAndCapacityResponse(String capacity, long totalRate) {
		this.capacity = capacity;
		this.totalRate = totalRate;
	}
	 
	
}

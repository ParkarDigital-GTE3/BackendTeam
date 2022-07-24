package com.AspasCabAdmin.Admin.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long locationId;
	private String source;
	private String destination;
	private long totalDistance;
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
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
	public long getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(long totalDistance) {
		this.totalDistance = totalDistance;
	}
	public Location(long locationId, String source, String destination, long totalDistance) {
		this.locationId = locationId;
		this.source = source;
		this.destination = destination;
		this.totalDistance = totalDistance;
	}
	public Location() {
		// TODO Auto-generated constructor stub
	}
	
}

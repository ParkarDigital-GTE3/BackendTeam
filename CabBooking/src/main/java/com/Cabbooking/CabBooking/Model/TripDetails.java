package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class TripDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trip_id;
	private String customerName;
	private String driverName;
	private String driverEmail;
	private String CustomerEmail;
	private String customerContactNo;
	private String driverContactNo;
	private String source;
	private String destination;
	private long totalDistance;
	private String cabType;
	private String cabCapacity;
	private String cabRegistrationNo;
	private long ratesPerKm;
	private int date;
	private long time;
	private long totalFare;
	public long getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(long trip_id) {
		this.trip_id = trip_id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverEmail() {
		return driverEmail;
	}
	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}
	public String getCustomerEmail() {
		return CustomerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}
	public String getCustomerContactNo() {
		return customerContactNo;
	}
	public void setCustomerContactNo(String customerContactNo) {
		this.customerContactNo = customerContactNo;
	}
	public String getDriverContactNo() {
		return driverContactNo;
	}
	public void setDriverContactNo(String driverContactNo) {
		this.driverContactNo = driverContactNo;
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
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}
	public String getCabCapacity() {
		return cabCapacity;
	}
	public void setCabCapacity(String cabCapacity) {
		this.cabCapacity = cabCapacity;
	}
	public String getCabRegistrationNo() {
		return cabRegistrationNo;
	}
	public void setCabRegistrationNo(String cabRegistrationNo) {
		this.cabRegistrationNo = cabRegistrationNo;
	}
	public long getRatesPerKm() {
		return ratesPerKm;
	}
	public void setRatesPerKm(long ratesPerKm) {
		this.ratesPerKm = ratesPerKm;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(long totalFare) {
		this.totalFare = totalFare;
	}
	public TripDetails(long trip_id, String customerName, String driverName, String driverEmail, String customerEmail,
			String customerContactNo, String driverContactNo, String source, String destination, long totalDistance,
			String cabType, String cabCapacity, String cabRegistrationNo, long ratesPerKm, int date, long time,
			long totalFare) {
		this.trip_id = trip_id;
		this.customerName = customerName;
		this.driverName = driverName;
		this.driverEmail = driverEmail;
		CustomerEmail = customerEmail;
		this.customerContactNo = customerContactNo;
		this.driverContactNo = driverContactNo;
		this.source = source;
		this.destination = destination;
		this.totalDistance = totalDistance;
		this.cabType = cabType;
		this.cabCapacity = cabCapacity;
		this.cabRegistrationNo = cabRegistrationNo;
		this.ratesPerKm = ratesPerKm;
		this.date = date;
		this.time = time;
		this.totalFare = totalFare;
	}

}
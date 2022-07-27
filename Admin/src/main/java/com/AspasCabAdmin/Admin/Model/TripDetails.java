package com.AspasCabAdmin.Admin.Model;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Entity
public class TripDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trip_id;
	private String customerName;
	private String driverName;
	private String driverEmail;
	private String customerEmail;
	private long booking_id;
	private String source;
	private String destination;
	private long totalDistance;
	private String cabType;
	private String cabCapacity;
	private String cabRegistrationNo;
	private long ratesPerKm;
	private String tripDate;
	private long totalFare;

	@Override
	public String toString() {
		return "TripDetails [trip_id=" + trip_id + ", customerName=" + customerName + ", driverName=" + driverName
				+ ", driverEmail=" + driverEmail + ", customerEmail=" + customerEmail + ", booking_id=" + booking_id
				+ ", source=" + source + ", destination=" + destination + ", totalDistance=" + totalDistance
				+ ", cabType=" + cabType + ", cabCapacity=" + cabCapacity + ", cabRegistrationNo=" + cabRegistrationNo
				+ ", ratesPerKm=" + ratesPerKm + ", tripDate=" + tripDate + ", totalFare=" + totalFare + "]";
	}

	public TripDetails(long trip_id, String customerName, String driverName, String driverEmail, String customerEmail,
			long booking_id, String source, String destination, long totalDistance, String cabType, String cabCapacity,
			String cabRegistrationNo, long ratesPerKm, String tripDate, long totalFare) {
		this.trip_id = trip_id;
		this.customerName = customerName;
		this.driverName = driverName;
		this.driverEmail = driverEmail;
		this.customerEmail = customerEmail;
		this.booking_id = booking_id;
		this.source = source;
		this.destination = destination;
		this.totalDistance = totalDistance;
		this.cabType = cabType;
		this.cabCapacity = cabCapacity;
		this.cabRegistrationNo = cabRegistrationNo;
		this.ratesPerKm = ratesPerKm;
		this.tripDate = tripDate;
		this.totalFare = totalFare;
	}

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
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public long getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(long booking_id) {
		this.booking_id = booking_id;
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

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public long getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(long totalFare) {
		this.totalFare = totalFare;
	}

	public TripDetails() {
		// TODO Auto-generated constructor stub
	}
	

}
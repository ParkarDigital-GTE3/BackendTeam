package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "CabBooking")
@DynamicUpdate
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long booking_id;
	private String source;
	private String destination;
	private String cabType;
	private String cabCapacity;
	private long customerId;
	private String customerName;
	private String custContactNo;
	private long driverId;
	@ColumnDefault("0")
	private String status;
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
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustContactNo() {
		return custContactNo;
	}
	public void setCustContactNo(String custContactNo) {
		this.custContactNo = custContactNo;
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Booking(long booking_id, String source, String destination, String cabType, String cabCapacity,
			long customerId, String customerName, String custContactNo, long driverId, String status) {
		this.booking_id = booking_id;
		this.source = source;
		this.destination = destination;
		this.cabType = cabType;
		this.cabCapacity = cabCapacity;
		this.customerId = customerId;
		this.customerName = customerName;
		this.custContactNo = custContactNo;
		this.driverId = driverId;
		this.status = status;
	}
	public Booking() {
		// TODO Auto-generated constructor stub
	}
	
	
}

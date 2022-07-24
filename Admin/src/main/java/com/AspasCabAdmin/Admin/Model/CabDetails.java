package com.AspasCabAdmin.Admin.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
public class CabDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cab_id;
    private String licenseNo;
    private String cab_name;
    private String cabRegistrationNo;
    private String cabType;
    private String cabCapacity;
    private String cabInsuranceNo;
    private String driverEmail;

	
	public CabDetails() {
		// TODO Auto-generated constructor stub
	}

	public CabDetails(long cab_id, String licenseNo, String cab_name, String cabRegistrationNo, String cabType,
		String cabCapacity, String cabInsuranceNo, String driverEmail) {
	this.cab_id = cab_id;
	this.licenseNo = licenseNo;
	this.cab_name = cab_name;
	this.cabRegistrationNo = cabRegistrationNo;
	this.cabType = cabType;
	this.cabCapacity = cabCapacity;
	this.cabInsuranceNo = cabInsuranceNo;
	this.driverEmail = driverEmail;
}

	public long getCab_id() {
		return cab_id;
	}

	public void setCab_id(long cab_id) {
		this.cab_id = cab_id;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getCab_name() {
		return cab_name;
	}

	public void setCab_name(String cab_name) {
		this.cab_name = cab_name;
	}

	public String getCabRegistrationNo() {
		return cabRegistrationNo;
	}

	public void setCabRegistrationNo(String cabRegistrationNo) {
		this.cabRegistrationNo = cabRegistrationNo;
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

	public String getCabInsuranceNo() {
		return cabInsuranceNo;
	}

	public void setCabInsuranceNo(String cabInsuranceNo) {
		this.cabInsuranceNo = cabInsuranceNo;
	}

	public String getDriverEmail() {
		return driverEmail;
	}

	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}

}

package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cab")
public class CabDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    private String licenseNo;
    private String cabRegistrationNo;
    private String cabType;
    private String cabCapacity;
    private String cabInsuranceNo;
    @OneToOne(fetch = FetchType.EAGER)
    private CabDriver driver_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
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
	public CabDriver getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(CabDriver driver_id) {
		this.driver_id = driver_id;
	}
	@Override
	public String toString() {
		return "CabDetails [id=" + id + ", licenseNo=" + licenseNo + ", cabRegistrationNo=" + cabRegistrationNo
				+ ", cabType=" + cabType + ", cabCapacity=" + cabCapacity + ", cabInsuranceNo=" + cabInsuranceNo
				+ ", driver_id=" + driver_id + "]";
	}
	public CabDetails(long id, String licenseNo, String cabRegistrationNo, String cabType, String cabCapacity,
			String cabInsuranceNo, CabDriver driver_id) {
		this.id = id;
		this.licenseNo = licenseNo;
		this.cabRegistrationNo = cabRegistrationNo;
		this.cabType = cabType;
		this.cabCapacity = cabCapacity;
		this.cabInsuranceNo = cabInsuranceNo;
		this.driver_id = driver_id;
	}
	public CabDetails() {
		// TODO Auto-generated constructor stub
	}
	
    
    
    

}

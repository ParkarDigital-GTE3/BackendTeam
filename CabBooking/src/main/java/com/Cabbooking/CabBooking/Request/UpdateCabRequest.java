package com.Cabbooking.CabBooking.Request;

public class UpdateCabRequest {
			
	private String licenseNo;
	private String cabRegistrationNo;
	private String cabType;
	private String cabCapacity;
	private String cabInsuranceNo;
	
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
	public UpdateCabRequest() {
		// TODO Auto-generated constructor stub
	}
	public UpdateCabRequest(String licenseNo, String cabRegistrationNo, String cabType, String cabCapacity,
			String cabInsuranceNo) {
		this.licenseNo = licenseNo;
		this.cabRegistrationNo = cabRegistrationNo;
		this.cabType = cabType;
		this.cabCapacity = cabCapacity;
		this.cabInsuranceNo = cabInsuranceNo;
	}
	
	

}

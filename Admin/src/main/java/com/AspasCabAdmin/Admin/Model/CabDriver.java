package com.AspasCabAdmin.Admin.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;


@Entity
public class CabDriver {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long driver_id;
	    private String role;
	    private String email;
	    private String contactNumber;
	    private String name;
//	    private String licenseNo;
	    private String dateOfBirth;
	    private String gender;
	    private String password;
	    private String activationStatus;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "cab_id")
	    private CabDetails cab_id;
		public CabDriver() {
			// TODO Auto-generated constructor stub
		}
		public long getDriver_id() {
			return driver_id;
		}
		public void setDriver_id(long driver_id) {
			this.driver_id = driver_id;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getActivationStatus() {
			return activationStatus;
		}
		public void setActivationStatus(String activationStatus) {
			this.activationStatus = activationStatus;
		}
		public CabDetails getCab_id() {
			return cab_id;
		}
		public void setCab_id(CabDetails cab_id) {
			this.cab_id = cab_id;
		}
		public CabDriver(long driver_id, String role, String email, String contactNumber, String name, String dateOfBirth,
				String gender, String password, String activationStatus, CabDetails cab_id) {
			this.driver_id = driver_id;
			this.role = role;
			this.email = email;
			this.contactNumber = contactNumber;
			this.name = name;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.password = password;
			this.activationStatus = activationStatus;
			this.cab_id = cab_id;
		}

		
		
}

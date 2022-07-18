package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
public class CabDriver {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    private String email;
    private String contactNumber;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String password;
    @ColumnDefault("0")
    private String activationStatus;
	public CabDriver() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public CabDriver(long id, String role, String email, String contactNumber, String name, String dateOfBirth,
			String gender, String password, String activationStatus) {
		this.id = id;
		this.role = role;
		this.email = email;
		this.contactNumber = contactNumber;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.password = password;
		this.activationStatus = activationStatus;
	}
	@Override
	public String toString() {
		return "CabDriver [id=" + id + ", role=" + role + ", email=" + email + ", contactNumber=" + contactNumber
				+ ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", password=" + password
				+ ", activationStatus=" + activationStatus + "]";
	}
	
	
	
	
}
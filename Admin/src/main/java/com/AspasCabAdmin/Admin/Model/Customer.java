package com.AspasCabAdmin.Admin.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Entity
public class Customer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    private String email;
    private String contactNo;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String password;
	public Customer() {
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	public Customer(long id, String role, String email, String contactNo, String name, String dateOfBirth,
			String gender, String password) {
		this.id = id;
		this.role = role;
		this.email = email;
		this.contactNo = contactNo;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", role=" + role + ", email=" + email + ", contactNo=" + contactNo + ", name="
				+ name + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", password=" + password + "]";
	}
	
}

package com.Cabbooking.CabBooking.Model;

import javax.persistence.*;

@Entity
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    private String email;
    private String userNumber;
   
    private String dateOfBirth;
    private String gender;
    private String password;
	public User() {
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
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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
	public User(long id, String role, String email, String userNumber, String dateOfBirth, String gender,
			String password) {
		this.id = id;
		this.role = role;
		this.email = email;
		this.userNumber = userNumber;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.password = password;
	}
	
	
    
	
}
    
    

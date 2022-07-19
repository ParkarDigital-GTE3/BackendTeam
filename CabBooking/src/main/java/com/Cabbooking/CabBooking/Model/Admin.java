package com.Cabbooking.CabBooking.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long admin_id;
	private String userName;
	private String password;
	public long getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(long admin_id, String userName, String password) {
		this.admin_id = admin_id;
		this.userName = userName;
		this.password = password;
	}
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
}

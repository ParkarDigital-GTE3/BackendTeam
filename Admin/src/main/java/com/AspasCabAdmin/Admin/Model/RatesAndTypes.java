package com.AspasCabAdmin.Admin.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RatesAndTypes {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String type;
	private String capacity;
	private long ratekm;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public long getRatekm() {
		return ratekm;
	}
	public void setRatekm(long ratekm) {
		this.ratekm = ratekm;
	}
	public RatesAndTypes(long id, String type, String capacity, long ratekm) {
		this.id = id;
		this.type = type;
		this.capacity = capacity;
		this.ratekm = ratekm;
	}
	public RatesAndTypes() {
		// TODO Auto-generated constructor stub
	}
	
}

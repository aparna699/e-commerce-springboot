package com.example.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//1.	Address ID
//2.	Unit Number
//3.	Address line 1
//4.	Address line 2
//5.	City
//6.	State
//7.	Pin code 
//8.	Country 
//9.    User ID

@Entity
@Table(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String pinCode;
	private String unit;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "User_id", referencedColumnName = "id")
	private User userId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CustomerOrder> orderId = new ArrayList<>();
	
	public Address() {
		super();
	}

	public Address(long id) {
		super();
		this.id = id;
	}



	public Address(String pinCode, String unit, String line1, String line2, String city, String state, String country,
			User userId) {
		super();
		this.pinCode = pinCode;
		this.unit = unit;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	
}

package com.example.ecommerce.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//1.	User ID
//2.	First Name
//3.	Last Name
//4.	Date Of Birth (DOB)
//5.	Phone Number 
//6.	Email
//7.	Password
//8.	Address ID
//9.	Payment ID
//10.   Role


@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dOB;
	
	private String role;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "userId")
    private List<Address> adresses = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cartId = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Review> reviewId = new ArrayList<>();

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//	private List<CustomerOrder> orderId = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(long id) {
		super();
		this.id = id;
	}



	public User(String firstName, String lastName, String email, String password, String phoneNumber, Date dOB,
			String role, List<Address> adresses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.dOB = dOB;
		this.role = role;
		this.adresses = adresses;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getdOB() {
		return dOB;
	}



	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}

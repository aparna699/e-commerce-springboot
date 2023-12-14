package com.example.ecommerce.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.ecommerce.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//1.	Order ID
//2.	User ID
//3.	Order Date
//4.	Payment ID//
//5.	Address ID
//6.	Order Total
//7.	Order Status ID

@Entity
@Table(name="CustomerOrder")
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User userId;
	
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="IST")
	private Date orderDate;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address addressId;

	private double totalCost;
 
	private Status statusId;

	//-------------------
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderLine> orderline = new ArrayList<>();

	public CustomerOrder() {
		super();
	}
	
	public CustomerOrder(long id) {
		super();
		this.id = id;
	}
	
	public CustomerOrder(User userId,Date orderDate, Address addressId, double totalCost, Status statusId) {
		super();
		this.userId = userId;
		this.orderDate = orderDate;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.statusId = statusId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Address getAddressId() {
		return addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Status getStatusId() {
		return statusId;
	}

	public void setStatusId(Status statusId) {
		this.statusId = statusId;
	}

}

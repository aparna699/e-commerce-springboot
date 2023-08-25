package com.example.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Status")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String statusMessage;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CustomerOrder> orderId = new ArrayList<>();

	public Status(String statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CustomerOrder> getOrderId() {
		return orderId;
	}

	public void setOrderId(List<CustomerOrder> orderId) {
		this.orderId = orderId;
	}

}

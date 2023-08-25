package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//1.	Order-line ID
//2.	item id 
//3.	Order ID
//4.	Qty
//5.	Price 

@Entity
@Table(name="OrderLine")
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Items itemId;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Items orderId;
	
	private int qty;
	private double price;
	
	public OrderLine(Items itemId, Items orderId, int qty, double price) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
		this.qty = qty;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Items getItemId() {
		return itemId;
	}
	public void setItemId(Items itemId) {
		this.itemId = itemId;
	}
	public Items getOrderId() {
		return orderId;
	}
	public void setOrderId(Items orderId) {
		this.orderId = orderId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}

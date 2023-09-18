package com.example.ecommerce.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//1.	id
//2.	userId
//3.	rating
//4.	title
//5.	comment
//6.	date

@Entity
@Table(name="Review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "User_id")
	private User userId;
	
	public Items getItemId() {
		return itemId;
	}

	public void setItemId(Items itemId) {
		this.itemId = itemId;
	}

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Items itemId;
	
	private float rate;
	private String title;
	private String comment;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date date;
	
	
	
	public Review() {
		super();
	}

	public Review(long id) {
		super();
		this.id = id;
	}


	public Review(long id, User userId, Items itemId, float rate, String title, String comment, Date date) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemId = itemId;
		this.rate = rate;
		this.title = title;
		this.comment = comment;
		this.date = date;
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

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

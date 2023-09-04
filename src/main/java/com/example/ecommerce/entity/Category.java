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

//1.	Id
//2.	Category name 

@Entity
@Table(name="Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String categoryName;
	private String categoryImgUrl;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="categoryId")
    private List<Items> items = new ArrayList<>();
	
	public Category() {
		super();
	}
	
	
	
	public Category(long id) {
		super();
		this.id = id;
	}

	public Category(String categoryName, String categoryImgUrl) {
		super();
		this.categoryName = categoryName;
		this.categoryImgUrl = categoryImgUrl;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getCategoryImgUrl() {
		return categoryImgUrl;
	}



	public void setCategoryImgUrl(String categoryImgUrl) {
		this.categoryImgUrl = categoryImgUrl;
	}
	
	
}

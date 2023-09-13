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

//1.	Product ID
//2.	Product Name 
//3.	Product images
//4.	Product Price
//5.	Quantity
//6.	Category 
//7.	Product description 


@Entity
@Table(name="Items")
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String productName;
//	private String productImage;
	private int price;
	private int qty;
	private String[] imgUrl;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category categoryId; 
	
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cartId = new ArrayList<>();

//	@OneToMany(cascade = CascadeType.ALL)
//	private List<OrderLine> orderline = new ArrayList<>();

	
	public Items() {
		super();
	}
	
	public Items(long id) {
		super();
		this.id = id;
	}



	public Items(String productName, int price, int qty, String[] imgUrl, Category categoryId, String description) {
		super();
		this.productName = productName;
		this.price = price;
		this.qty = qty;
		this.imgUrl = imgUrl;
		this.categoryId = categoryId;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String[] imgUrl) {
		this.imgUrl = imgUrl;
	}

//	public List<CartItem> getCartId() {
//		return cartId;
//	}
//
//	public void setCartId(List<CartItem> cartId) {
//		this.cartId = cartId;
//	}

	
	
}

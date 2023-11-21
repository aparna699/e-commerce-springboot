package com.example.ecommerce.dto;

import com.google.gson.annotations.SerializedName;

public class CreatePayment {
	 @SerializedName("items")
	 CreatePaymentItem[] items;
	 private long amount ;
	 
	public CreatePayment(CreatePaymentItem[] items, long amount) {
		super();
		this.items = items;
		this.amount = amount;
	}
	public CreatePaymentItem[] getItems() {
		return items;
	}
	public void setItems(CreatePaymentItem[] items) {
		this.items = items;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
}

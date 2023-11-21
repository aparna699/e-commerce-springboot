package com.example.ecommerce.dto;

import com.google.gson.annotations.SerializedName;

public class CreatePaymentItem {
	@SerializedName("id")
    private int id;

    public CreatePaymentItem() {
		super();
	}

	public CreatePaymentItem(int id) {
		super();
		this.id = id;
	}

	public int getId() {
      return id;
    }

	public void setId(int id) {
		this.id = id;
	}
}

package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CustomerOrder;
import com.example.ecommerce.entity.Items;
import com.example.ecommerce.repository.CustomerOrderRepository;

@RestController
@RequestMapping("/")
public class CustomerOrderController {
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	//Get all orders
	@GetMapping("api/order")
	public List<CustomerOrder> getAllCustomerOrder(){
		return this.customerOrderRepository.findAll();
	}
	
	//Post create order
	@PostMapping("api/order")
	public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
		return this.customerOrderRepository.save(customerOrder);
	}
}

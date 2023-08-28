package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.CustomerOrder;
import com.example.ecommerce.exception.ResourceNotFoundException;
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
	
	//get order by id
	@GetMapping("api/order/{id}")
	public CustomerOrder getCustomerOrderById(@PathVariable(value = "id") long  customerOrderId){
		return this.customerOrderRepository.findById(customerOrderId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ customerOrderId));
	}
	
	//get user orders
	@GetMapping("api/order/address/{id}")
	public List<CustomerOrder> getCustomerOrderByAddressId(@PathVariable(value = "id") long  id){
		Address address = new Address(id);
		return this.customerOrderRepository.findByAddressId(address);
	}
	
	//Post create order
	@PostMapping("api/order")
	public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
		return this.customerOrderRepository.save(customerOrder);
	}
}

package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CustomerOrder;
import com.example.ecommerce.entity.OrderLine;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.OrderLineRepository;

@RestController
@RequestMapping("/")
public class OrderLineController {
	@Autowired
	private OrderLineRepository orderLineRepository;
	
	//get all 
	@GetMapping("api/order-line")
	public List<OrderLine> getAllCustomerOrder(){
		return this.orderLineRepository.findAll();
	}
	
	//get order-line by id
	@GetMapping("api/order-line/{id}")
	public OrderLine getCustomerOrderById(@PathVariable(value = "id") long  orderLineId){
		return this.orderLineRepository.findById(orderLineId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ orderLineId));
	}
	
	//get order-line by order id
	@GetMapping("api/order-line/orders/{orderId}")
	public List<OrderLine> getCustomerOrderByOrderId(@PathVariable(value = "orderId") long  orderId){
		CustomerOrder customerOrder = new CustomerOrder(orderId);
		return this.orderLineRepository.findByOrderId(customerOrder);
	}
	
	//add order-line
	@PostMapping("api/order-line")
	public OrderLine createOrderLine(@RequestBody OrderLine orderLine) {
		return this.orderLineRepository.save(orderLine);
	}
	
	
}

package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CartItemRepository;

@RestController
@RequestMapping("/")
public class CartItemController {
	@Autowired
	private CartItemRepository cartItemRepository;
	
	//get all
	@GetMapping("api/cart-item")
	public List<CartItem> getAllCartItem(){
		return this.cartItemRepository.findAll();
	}
	
	//get by id 
	@GetMapping("api/cart-item/{id}")
	public CartItem getCustomerOrderById(@PathVariable(value = "id") long  cartItemId){
		return this.cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ cartItemId));
	}
	
	//get by user id
	
	//post
	@PostMapping("api/cart-item")
	public CartItem createCartItem(@RequestBody CartItem cartItem) {
		return this.cartItemRepository.save(cartItem);
	}
	
	//put 
	@PutMapping("api/cart-item/{id}")
	public CartItem updateUser(@RequestBody CartItem cartItem, @PathVariable(value = "id") long  cartItemId){
		CartItem existingCartItem = this.cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ cartItemId));
		existingCartItem.setUserId(cartItem.getUserId()!= null ? 
				cartItem.getUserId(): existingCartItem.getUserId());
		existingCartItem.setItemId(cartItem.getItemId()!= null ? 
				cartItem.getItemId(): existingCartItem.getItemId());
		existingCartItem.setQty(cartItem.getQty()!= 0 ? 
				cartItem.getQty(): existingCartItem.getQty());
		return this.cartItemRepository.save(existingCartItem);
	}
	
	
	//delete
	@DeleteMapping("api/cart-item/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long  cartItemId) {
		CartItem existingCartItem = this.cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ cartItemId));
		this.cartItemRepository.delete(existingCartItem);
		return ResponseEntity.ok().build();
	}
	
}

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

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Items;
import com.example.ecommerce.entity.OrderLine;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.ItemsRepository;


@RestController
@RequestMapping("/")
public class ItemsController {
	@Autowired
	private ItemsRepository itemsRepository;
	
	//Get All Items
	@GetMapping("api/items")
	public List<Items> getAllItems(){
		return this.itemsRepository.findAll();
	}
	
	//Add Items
	@PostMapping("api/items")
	public Items createItems(@RequestBody Items items) {
		return this.itemsRepository.save(items);
	}
	
	//Get Item by Id
	@GetMapping("api/items/{id}")
	public Items getItemsById(@PathVariable(value = "id") long  itemsId){
		return this.itemsRepository.findById(itemsId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ itemsId));
	}
	
	//Get Items by category 
	@GetMapping("api/items/category/{categoryId}")
	public List<Items> getItemsByCategoryId(@PathVariable(value = "categoryId") long  categoryId){
		Category category = new Category(categoryId);
		return this.itemsRepository.findByCategoryId(category);
	}
	
	//Edit Item
	@PutMapping("api/items/{id}")
	public Items updateItems(@RequestBody Items items, @PathVariable(value = "id") long itemsId) {
		Items existingItems = this.itemsRepository.findById(itemsId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ itemsId));
		existingItems.setProductName(items.getProductName()!= null ?
				items.getProductName(): existingItems.getProductName());
		existingItems.setPrice(items.getPrice()!= 0 ?
				items.getPrice(): existingItems.getPrice());
		existingItems.setQty(items.getQty()!= 0 ?
				items.getQty(): existingItems.getQty());
		existingItems.setCategoryId(items.getCategoryId()!= null ?
				items.getCategoryId(): existingItems.getCategoryId());
		existingItems.setDescription(items.getDescription()!= null ?
				items.getDescription(): existingItems.getDescription());
		existingItems.setImgUrl(items.getImgUrl()!= null ?
				items.getImgUrl(): existingItems.getImgUrl());
		return this.itemsRepository.save(existingItems);
	}
	
	@PutMapping("api/items/reduceQty/{id}")
	public Items reduceItemsQty(@RequestBody Items items, @PathVariable(value = "id") long itemsId) {
		Items existingItems = this.itemsRepository.findById(itemsId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ itemsId));
		existingItems.setQty(items.getQty()!= 0 ?
				existingItems.getQty() - items.getQty(): existingItems.getQty());
		return this.itemsRepository.save(existingItems);
	}
	//Delete Itemreturn this.itemsRepository.save(existingItems);
	@DeleteMapping("api/items/{id}")
	public ResponseEntity<User> deleteItems(@PathVariable(value = "id")long itemsId){
		Items existingItems = this.itemsRepository.findById(itemsId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ itemsId));
		this.itemsRepository.delete(existingItems);
		return ResponseEntity.ok().build();
	}

}

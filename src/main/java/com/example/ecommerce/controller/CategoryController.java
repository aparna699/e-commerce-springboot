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
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CategoryRepository;

@RestController
@RequestMapping("/")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
//	GET: Get All Category
	@GetMapping("api/category")
	public List<Category> getAllCategory(){
		return this.categoryRepository.findAll();
	}
	
//	Add Category 
	@PostMapping("api/category")
	public Category createCategory(@RequestBody Category category) {
		return this.categoryRepository.save(category);
	}
	
//	Get Category by id
	@GetMapping("api/category/{id}")
	public Category getCategoryById(@PathVariable(value = "id")long categoryId ) {
		return this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ categoryId));
	}
	
//	Edit Category
	@PutMapping("api/category/{id}")
	public Category updateCategory(@RequestBody Category category, @PathVariable(value = "id") long categoryId ) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ categoryId));
		existingCategory.setCategoryName(category.getCategoryName()!= null ? 
				category.getCategoryName(): existingCategory.getCategoryName());
		existingCategory.setCategoryImgUrl(category.getCategoryImgUrl()!= null ? 
				category.getCategoryImgUrl(): existingCategory.getCategoryImgUrl());
		return this.categoryRepository.save(existingCategory);
	}
	
//	Delete Category
	@DeleteMapping("api/category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable(value = "id") long  categoryId) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ categoryId));
		this.categoryRepository.delete(existingCategory);
		return ResponseEntity.ok().build();
	}

}

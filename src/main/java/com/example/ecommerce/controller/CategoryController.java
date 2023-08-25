package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Category;
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
//	Edit Category
//	Delete Category

}

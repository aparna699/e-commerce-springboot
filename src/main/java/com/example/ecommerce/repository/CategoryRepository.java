package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Items;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}

package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
	List<Items> findByCategoryId(Category category);
}

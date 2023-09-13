package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByUserId(User user);
}

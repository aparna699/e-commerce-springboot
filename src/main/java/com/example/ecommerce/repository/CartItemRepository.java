package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByUserId(User user);
}

package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.OrderLine;
import com.example.ecommerce.entity.User;


@Repository 
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByEmail(String email);
//	List<OrderLine> findOrderLineByUserId(User user);
}

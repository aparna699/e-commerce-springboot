package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Items;
import com.example.ecommerce.entity.Review;
//import com.example.ecommerce.entity.User;

@Repository 
public interface ReviewRepository extends JpaRepository<Review,Long>{
	List<Review> findByItemId(Items item);
}

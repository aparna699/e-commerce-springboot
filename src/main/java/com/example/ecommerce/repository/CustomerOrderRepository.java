package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.CustomerOrder;
import com.example.ecommerce.entity.User;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{
	List<CustomerOrder> findByUserId(User user);
}

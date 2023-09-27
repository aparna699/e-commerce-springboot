package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.OrderLine;
import com.example.ecommerce.entity.CustomerOrder;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{
	List<OrderLine> findByOrderId(CustomerOrder customerOrder);
}

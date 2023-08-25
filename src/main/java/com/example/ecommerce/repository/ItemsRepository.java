package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

}

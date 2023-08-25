package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.User;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{
	List<Address> findByUserId(User userId);
}

package com.example.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.AddressRepository;

@RestController
@RequestMapping("/")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	
	//GET All Addresses
	@GetMapping("api/address")
	public List<Address> getAllAddresses(){
		return this.addressRepository.findAll();
	}
	
	//POST add new address
	@PostMapping("api/address")
	public Address addAddress(@RequestBody Address address) {
		return this.addressRepository.save(address);
	}
	
	//GET address by ID
	@GetMapping("api/address/{id}")
	public Address getAddressById(@PathVariable(value = "id") long  addressId){
		return this.addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ addressId));
	}
	
//	//GET address by userID
//	@GetMapping("api/address/user/{userId}")
//	public List<Address> getAddressByUserId(@PathVariable(value = "userId") long  userId){
//		User user = new User(userId);
//		return ((List<Address>) this.addressRepository.findByUserId(user))
//				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ userId));
//	}
	
	//DELETE address 
	@DeleteMapping("api/address/{id}")
	public ResponseEntity<Address> deleteAddress(@PathVariable(value = "id") long addressId){
		Address existingAddress = this.addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ addressId));
		this.addressRepository.delete(existingAddress);
		return ResponseEntity.ok().build();
	}
}

package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.OrderLine;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	// GET all user
	@GetMapping("api/users")
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}
	
	//POST insert users create user
	@PostMapping("api/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User createUser(@RequestBody User user) {
		String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encoded);
		return this.userRepository.save(user);
	}
	
	//GET user by ID
	@GetMapping("api/users/{id}")
	@PreAuthorize("permitAll()")
	public User getUserById(@PathVariable(value = "id") long  userId){
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ userId));
	}
	
	//update user
	@PutMapping("api/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable(value = "id") long  userId){
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ userId));
		existingUser.setFirstName(user.getFirstName()!= null ? 
				user.getFirstName(): existingUser.getFirstName());
		existingUser.setLastName(user.getLastName()!= null ? 
				user.getLastName(): existingUser.getLastName());
		existingUser.setEmail(user.getEmail() != null ? 
				user.getEmail(): existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? 
				new BCryptPasswordEncoder().encode(user.getPassword()): existingUser.getPassword());
		existingUser.setdOB(user.getdOB() != null ? 
				user.getdOB(): existingUser.getdOB());
		existingUser.setRole(user.getRole() != null ? 
				user.getRole(): existingUser.getRole());
		
		
		return this.userRepository.save(existingUser);
	}
	
	//DELETE user
	@DeleteMapping("api/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long  userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
	
	
}

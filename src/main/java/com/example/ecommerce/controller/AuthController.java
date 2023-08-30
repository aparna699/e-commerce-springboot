package com.example.ecommerce.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.AuthRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.JwtService;

@RestController
@RequestMapping("/")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	
	@PostMapping("/auth")
	public HashMap<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		User user = this.userRepository.findByEmail(authRequest.getEmail())
		.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ authRequest.getEmail()));
//		System.out.println();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
				);
        if (authentication.isAuthenticated()) {
        	HashMap<String, String> map = new HashMap<>();
            map.put("accessToken", jwtService.generateToken(authRequest.getEmail(), user.getRole(), user.getId()));
            map.put("role",  user.getRole());
            map.put("id",  user.getId()+"");
   
            return map;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
	}
}

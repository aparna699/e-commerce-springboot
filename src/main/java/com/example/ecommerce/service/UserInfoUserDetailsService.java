package com.example.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.ecommerce.config.UserInfoUserDetails;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Optional<User> user = repository.findByEmail(email); 
    	
    	return user.map(UserInfoUserDetails::new)
    	.orElseThrow(() -> new UsernameNotFoundException("user not found"));

    }
    
    
}

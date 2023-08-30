package com.example.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ecommerce.jwt.JwtAuthFilter;
import com.example.ecommerce.service.UserInfoUserDetailsService;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {
	 @Autowired
	 private JwtAuthFilter authFilter;
	 
	 
	 @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
//	        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> 
	          auth.requestMatchers("/auth").permitAll()
	              .requestMatchers("/api/users/**").hasAnyRole("CUSTOMER")
	              .anyRequest().authenticated()
	        );
	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
	 @Bean
	 public UserDetailsService userDetailsService() {
		 return new UserInfoUserDetailsService();
	 }
	 
	 @Bean
	 public PasswordEncoder encoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 @Bean
	 public AuthenticationProvider authenticationProvider(){
		 DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	     authenticationProvider.setUserDetailsService(userDetailsService());
	     authenticationProvider.setPasswordEncoder(encoder());
	     return authenticationProvider;
	 }
		
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	 	
	 	
}
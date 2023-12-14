package com.example.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.ecommerce.jwt.JwtAuthFilter;
import com.example.ecommerce.service.UserInfoUserDetailsService;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {
	 @Autowired
	 private JwtAuthFilter authFilter;
	 
	 
	 @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	    .cors(Customizer.withDefaults())
	    	.csrf(csrf -> csrf.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> 
	          auth.requestMatchers("/auth","/api/review","/api/review/item/**").permitAll()
	          	  .requestMatchers(HttpMethod.GET,
	          			  "/api/category",
	          			  "/api/category/**",
	          			  "/api/items/**",
	          			  "/api/order/**",
	          			  "/api/items",
	          			  "/api/items/**",
	          			  "/api/items/category/**",
	          			  "/api/order-line/**",
	          			  "/api/users/**",
	          			  "/api/items/reduceQty/**",
	          			  "/api/order-line/orders/**").permitAll()
	          	  .requestMatchers(HttpMethod.GET,
	          			  "/api/users",
	          			  "/api/users/**",
	          			  "/api/address/**",
	            		  "/api/address/user/**",
	            		  "/api/cart-item", 
	            		  "api/cart-item/**",
	            		  "/api/cart-item/user/**",
	            		  "/api/order/user/**",
	            		  "/api/users/**",
	            		  "/api/order-line",
	            		  "/api/order-line/**",
//	            		  "/api/order-line/orders/**",
	            		  "/api/order-line/user/**").hasAnyRole("ADMIN","CUSTOMER")
	          	  .requestMatchers(HttpMethod.GET,
	          			  "/api/address",
	          			  "/api/order",
	          			  "/api/order/**"
	          			  ).hasAnyRole("ADMIN")
	          	  //POST
	          	  .requestMatchers(HttpMethod.POST,
	          			  "/api/users").permitAll()
	          	  .requestMatchers(HttpMethod.POST,
	          			  "/api/address",
	          			  "/api/cart-item",
	          			  "/create-payment-intent",
	          			  "/create-payment-intent/**",
	          			  "/api/order",
	          			  "/api/order-line").hasAnyRole("ADMIN","CUSTOMER")
	          	  .requestMatchers(HttpMethod.POST,
	          			  "/api/category",
	          			  "/api/items").hasAnyRole("ADMIN")
	          	  //PUT
	          	  .requestMatchers(HttpMethod.PUT, "/api/review/**").permitAll()
	          	  .requestMatchers(HttpMethod.PUT, 
	          			  "/api/users/**",
	          			  "/api/address/**",
	          			  "/api/cart-item/**").hasAnyRole("ADMIN","CUSTOMER")
	          	  .requestMatchers(HttpMethod.PUT,
	          			  "/api/category",
	          			  "/api/items/**").hasAnyRole("ADMIN")
	          	  //DELETE
	          	  .requestMatchers(HttpMethod.DELETE, 
	          			  "/api/users/**",
	          			  "/api/address/**",
	          			  "/api/review/**",
	          			  "/api/cart-item/**").hasAnyRole("ADMIN","CUSTOMER")
	          	  .requestMatchers(HttpMethod.DELETE,
	          			  "/api/category/**",
	          			  "/api/items/**").hasAnyRole("ADMIN")
	              
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
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
////		configuration.s
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
//		configuration.setAllowCredentials(true);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	 	
}
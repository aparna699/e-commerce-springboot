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
	          auth.requestMatchers("/auth","/api/category/**","/api/cart-item/user/**","/api/users","/api/review","/api/review/item/**").permitAll()
	          	  .requestMatchers(HttpMethod.GET,"/api/items/**").permitAll()
	          	  .requestMatchers(HttpMethod.PUT, "/api/review/**").permitAll()
	          	  .requestMatchers(HttpMethod.POST,"/api/items").permitAll()
	          	  .requestMatchers(HttpMethod.DELETE,"/api/cart-item/**").hasAnyRole("ADMIN","CUSTOMER")
	          	  .requestMatchers(HttpMethod.DELETE,"/api/items/**").hasAnyRole("ADMIN")
	              .requestMatchers(HttpMethod.GET,"/api/**","api/address/user/**","api/cart-item").hasAnyRole("ADMIN","CUSTOMER")
	              .requestMatchers(HttpMethod.GET,"/api/users/{id}").hasAnyRole("ADMIN","CUSTOMER")
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
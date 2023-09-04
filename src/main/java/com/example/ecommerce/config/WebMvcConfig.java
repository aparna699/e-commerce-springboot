package com.example.ecommerce.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//		.allowedMethods("GET", "PUT", "POST", "DELETE")
//		.allowedOrigins("http://localhost:3000")
//		.allowCredentials(true);
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods(
				HttpMethod.GET.name(),
				HttpMethod.PUT.name(),
				HttpMethod.POST.name(),
				HttpMethod.DELETE.name()
				)
		.allowedHeaders(
				HttpHeaders.CONTENT_TYPE,
				HttpHeaders.AUTHORIZATION
				)
		.allowedOrigins("http://localhost:3000")
		.allowCredentials(true);
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

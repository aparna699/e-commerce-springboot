package com.example.ecommerce.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	public static final String SECRET = "6351665468576D5A7134743777217A25432A462D4A614E645267556B586E3272";
	
	public String generateToken(String email, String role, long id ) {//String email,String role
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims,email, role, id);// email, role
	}

	private String createToken(Map<String, Object> claims, String email, String role, long id) {//String email, String role
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.claim("role", role)
				.claim("id", id)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))//30min
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	
	
	

	private Key getSignKey() {
		byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);

	}
	//
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	private Claims extractAllClaims(String token) {
		return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
	}

	public String extractEmail(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}
	
	 public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	 private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractEmail(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	 
} 
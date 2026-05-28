package com.esmael.taskmanager.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	 @Value("${jwt.secret}")
	 private String secretKey;
	
	 private final long EXPIRATION = 1000 * 60 * 60; 
	 
	 
	  private Key getSigningKey() {
	        return Keys.hmacShaKeyFor(
	                secretKey.getBytes()
	        );
	    }
	 
	    public String generateToken(String email) {
	    	System.out.println("--------------------------------------------------------------------------------------");
	    	System.out.println("JWT SECRET = " + secretKey);

	        return Jwts.builder()
	                .setSubject(email)
	                .setIssuedAt(new Date())
	                .setExpiration(
	                        new Date(
	                                System.currentTimeMillis()
	                                        + EXPIRATION
	                        )
	                )
	                .signWith(
	                        getSigningKey(),
	                        SignatureAlgorithm.HS256
	                )
	                .compact();
	    }

	    public String extractUsername(String token) {

	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
	 public boolean validateToken(String token, String email) {

	        String username = extractUsername(token);

	        return username.equals(email);
	    }

}

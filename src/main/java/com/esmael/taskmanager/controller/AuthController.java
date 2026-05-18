package com.esmael.taskmanager.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esmael.taskmanager.dto.AuthRequest;
import com.esmael.taskmanager.dto.AuthResponse;
import com.esmael.taskmanager.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 private final AuthenticationManager authenticationManager;
	 private final JwtUtil jwtUtil;

	    public AuthController(AuthenticationManager authenticationManager,
	                          JwtUtil jwtUtil) {

	        this.authenticationManager = authenticationManager;
	        this.jwtUtil = jwtUtil;
	    }
	    
	    @PostMapping("/login")
	    public AuthResponse login(
	            @RequestBody AuthRequest request) {

	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()
	                )
	        );

	        String token =
	                jwtUtil.generateToken(request.getEmail());

	        return new AuthResponse(token);
	    }


}

package com.esmael.taskmanager.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esmael.taskmanager.dto.UserRequestDto;
import com.esmael.taskmanager.dto.UserResponseDto;
import com.esmael.taskmanager.entity.Role;
import com.esmael.taskmanager.entity.User;
import com.esmael.taskmanager.exception.ResourceNotFoundException;
import com.esmael.taskmanager.repository.RoleRepository;
import com.esmael.taskmanager.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final RoleRepository roleRepository;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		
		//  , PasswordEncoder passwordEncoder   
	}
	
	public UserResponseDto createUser(UserRequestDto request) {
		
		User user = new User();
		
		user.setUsername(request.getName());
		user.setEmail(request.getEmail());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User saved = userRepository.save(user);
		
		
		
		
	    
		return  new UserResponseDto(saved.getId(),saved.getUsername(),saved.getEmail());
	}	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
		
	}
	public void deleteUser(Long id) {
		User user = getUserById(id);
		userRepository.delete(user);
		
	}
}

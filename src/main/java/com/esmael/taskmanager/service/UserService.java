package com.esmael.taskmanager.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esmael.taskmanager.entity.User;
import com.esmael.taskmanager.exception.ResourceNotFoundException;
import com.esmael.taskmanager.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
		
		//  , PasswordEncoder passwordEncoder   
	}
	
	public User createUser(User user) {
		
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
		return userRepository.save(user);
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

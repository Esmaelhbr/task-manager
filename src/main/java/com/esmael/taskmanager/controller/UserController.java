package com.esmael.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esmael.taskmanager.dto.ApiResponse;
import com.esmael.taskmanager.dto.UserRequestDto;
import com.esmael.taskmanager.dto.UserResponseDto;
import com.esmael.taskmanager.entity.User;
import com.esmael.taskmanager.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto request){
		UserResponseDto created = userService.createUser(request);
		
		 ApiResponse<UserResponseDto> response =
		            new ApiResponse<>(
		                    true,
		                    "User created successfully",
		                    created
		            );

		  return ResponseEntity.status(201).body(response);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {

	     List<User> users = userService.getAllUsers();

	     ApiResponse<List<User>> response =
	                new ApiResponse<>(true, "Users retrieved successfully", users);

	     return ResponseEntity.ok(response);
	 }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {

	      User user = userService.getUserById(id);

	      ApiResponse<User> response =
	                new ApiResponse<>(true, "User retrieved successfully", user);

	      return ResponseEntity.ok(response);
	    }

	    // Delete user
	  @DeleteMapping("/{id}")
	  public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable Long id) {

	        userService.deleteUser(id);

	        ApiResponse<Object> response =
	                new ApiResponse<>(true, "User deleted successfully", null);

	        return ResponseEntity.ok(response);
	    }


	
	

}

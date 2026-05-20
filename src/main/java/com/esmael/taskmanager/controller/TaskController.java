package com.esmael.taskmanager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esmael.taskmanager.dto.ApiResponse;
import com.esmael.taskmanager.entity.Task;
import com.esmael.taskmanager.service.TaskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
		
	}
	@PostMapping
	public ResponseEntity<ApiResponse<Task>> createTask(@Valid @RequestBody Task task) {
		 Task created = taskService.createTask(task);
		 
		 ApiResponse<Task> response = new ApiResponse<Task>(true, "Task created successfully", created);
		 return ResponseEntity.status(201).body(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<Task>> getTasks(@RequestParam(defaultValue = "0")
	        int page,@RequestParam(defaultValue = "5") int size, 
	        @RequestParam(defaultValue = "id")
	        String sortBy) {

	    return ResponseEntity.ok(
	            taskService.getTasks(
	                    page,
	                    size,
	                    sortBy
	            )
	    );
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable Long id) {
	    Task task = taskService.getTaskById(id);
	    
	    ApiResponse<Task> response = new ApiResponse<Task>(true, "Task retrieved successfully", task);
		
	    return ResponseEntity.ok(response);
	}


	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@Valid @PathVariable Long id, @RequestBody Task task){
		try {
			return ResponseEntity.ok(taskService.updateTask(id, task));
			
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
	//pagination
	@GetMapping("/search")
	public ResponseEntity<Page<Task>> searchTasks(@RequestParam String keyword,
			@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		
		return ResponseEntity.ok(taskService.searchTasks(keyword, page, size));
		
		
	}

}

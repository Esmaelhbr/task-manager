package com.esmael.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		 Task created = taskService.createTask(task);
		 return ResponseEntity.status(201).body(created);
	}
	
	@GetMapping
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id){
		
		return taskService.getTaskById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
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

}

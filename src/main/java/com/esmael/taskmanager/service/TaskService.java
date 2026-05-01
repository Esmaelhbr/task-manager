package com.esmael.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esmael.taskmanager.entity.Task;
import com.esmael.taskmanager.exception.ResourceNotFoundException;
import com.esmael.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Task getTaskById(Long id){
		return taskRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
	}
	
	public Task updateTask(Long id, Task updatedTask) {
		 Task task = taskRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

		    task.setTitle(updatedTask.getTitle());
		    task.setDescription(updatedTask.getDescription());
		    task.setCompleted(updatedTask.isCompleted());

		    return taskRepository.save(task);
	}
	
	public void deleteTask(Long id) {
		  Task task = taskRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

		    taskRepository.delete(task);
	}

	
	
}

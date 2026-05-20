package com.esmael.taskmanager.service;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	//pagination 
	public Page<Task> getTasks(
	    
		int page,
	    int size,
	    String sortBy) {

	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

	    return taskRepository.findAll(pageable);
	}
	
	public Page<Task> searchTasks(String keyword, int page, int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		return taskRepository.findByTitleContainingIgnoreCase(keyword, pageable);
		
	}
	
	
	

	
	
}

package com.esmael.taskmanager.service;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.esmael.taskmanager.entity.Task;
import com.esmael.taskmanager.entity.User;
import com.esmael.taskmanager.exception.ResourceNotFoundException;
import com.esmael.taskmanager.repository.TaskRepository;
import com.esmael.taskmanager.repository.UserRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	
	public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}
	
	public Task createTask(Long userId, Task task) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));;
		task.setUser(user);
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
	
	
	public List<Task> getTasksByuser(Long userId){
		if(!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("User not found with id: " + userId);
		}
		return taskRepository.findByUserId(userId);
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

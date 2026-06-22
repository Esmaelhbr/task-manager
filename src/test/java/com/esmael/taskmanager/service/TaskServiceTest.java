package com.esmael.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esmael.taskmanager.entity.Task;
import com.esmael.taskmanager.entity.User;
import com.esmael.taskmanager.exception.ResourceNotFoundException;
import com.esmael.taskmanager.repository.TaskRepository;
import com.esmael.taskmanager.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	
	@Mock
	private TaskRepository taskRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private TaskService taskService;
	
	@Test
	void shouldCreateTask() {
		User user = new User();
		user.setId(1L);
		
		Task task = new Task();
		task.setTitle("Learn Mockito");
		
		 when(userRepository.findById(1L))
         .thenReturn(Optional.of(user));

		 when(taskRepository.save(any(Task.class)))
         .thenAnswer(invocation ->
                 invocation.getArgument(0));

		
		Task result = taskService.createTask(1L, task);
		
		assertNotNull(result);
		assertEquals("Learn Mockito", result.getTitle());
		
		verify(taskRepository).save(any(Task.class));
		
	}
	
	@Test
	void shouldThrowWhenUserNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> taskService.createTask(1L, new Task()));
	}
	
	@Test
	void shouldDeleteTask() {
		Task task = new Task();
		task.setId(1L);
		
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
		
		taskService.deleteTask(1L);
		
		verify(taskRepository, times(1)).delete(task);
	}
	
	@Test
	void shouldThrowExceptionWhenDeletingMissingTask() {
		when(taskRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> taskService.deleteTask(1L));
		
		verify(taskRepository, never()).delete(any(Task.class));
	}
	
	@Test
	void shouldReturnTaskById() {

	    Task task = new Task();
	    task.setId(1L);
	    task.setTitle("Learn JUnit");

	    when(taskRepository.findById(1L))
	            .thenReturn(Optional.of(task));

	    Task result =
	            taskService.getTaskById(1L);

	    assertNotNull(result);

	    assertEquals(
	            1L,
	            result.getId()
	    );

	    assertEquals(
	            "Learn JUnit",
	            result.getTitle()
	    );

	    verify(taskRepository)
	            .findById(1L);
	}

}

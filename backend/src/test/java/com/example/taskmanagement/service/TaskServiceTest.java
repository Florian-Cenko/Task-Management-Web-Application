package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Unit tests for business logic using Mockito (no Spring context needed)
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService; // Injects the mock repository into the service

    @Test
    void createTask_shouldReturnSavedTask() {
        Task task = new Task();
        task.setTitle("Test Task 1");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals("Test Task 1", result.getTitle());
        verify(taskRepository).save(task);
    }

    @Test
    void getAllTasks_shouldReturnAllTasks() {
        List<Task> tasks = List.of(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(tasks.size(), result.size());
        verify(taskRepository).findAll();
    }

    @Test
    void getATask_shouldReturnTaskWhenFound() {
        Long taskId = 1L;
        Task task = new Task();
        task.setTitle("Test Task 3");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getATask(taskId);

        assertEquals(task.getTitle(), result.getTitle());
        verify(taskRepository).findById(taskId);
    }

    @Test
    void updateTask_shouldReturnUpdatedTask() {
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setTitle("Old Test Task 4");

        Task updatedTask = new Task();
        updatedTask.setTitle("New Test Task 4");

        // Mock finding the existing task and saving the updated one
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        Task result = taskService.updateTask(taskId, updatedTask);

        assertEquals("New Test Task 4", result.getTitle());
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void updateTask_shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        Task updatedTask = new Task();
        updatedTask.setTitle("New Test Task 5");

        // Simulate task not existing in DB
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Expect our custom exception to be thrown and save() never to be called
        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.updateTask(taskId, updatedTask);
        });

        verify(taskRepository).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void deleteTask_shouldDeleteTask() {
        Long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        verify(taskRepository).existsById(taskId);
        verify(taskRepository).deleteById(taskId);
    }

    @Test
    void deleteTask_shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        // Expect ResourceNotFoundException if ID doesn't exist
        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.deleteTask(taskId);
        });

        verify(taskRepository).existsById(taskId);
        verify(taskRepository, never()).deleteById(taskId);
    }
}
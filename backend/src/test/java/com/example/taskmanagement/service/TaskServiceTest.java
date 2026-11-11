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


@ExtendWith(MockitoExtension.class)
class TaskServiceTest{

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    //First Test: I want to see if a task is created successfully
    @Test
    void createTask_shouldReturnSavedTask(){

        // Arrange
        Task task = new Task();
        task.setTitle("Test Task 1");

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        //Act
        Task result = taskService.createTask(task);

        //Assert
        assertNotNull(result);
        assertEquals("Test Task 1", result.getTitle());

        verify(taskRepository).save(task);
    }

    // Second Test : I want to see if the getAllTasks method runs correclty
    @Test
    void getAllTasks_shouldReturnAllTasks(){

        //Arrange
        List<Task> tasks = List.of(new Task(), new Task());

        when(taskRepository.findAll()).thenReturn(tasks);

        //Act
        List<Task> result = taskService.getAllTasks();

        //Assert
        assertEquals(tasks.size(), result.size());

        verify(taskRepository).findAll();

    }

    // Third Test : I want to see if the getATask method runs correclty
    @Test
    void getATask_shouldReturnTaskWhenFound(){

        //Arrange
        Long taskId = 1L;
        Task task = new Task();

        task.setTitle("Test Task 3");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        //Act
        Task result = taskService.getATask(taskId);

        //Assert
        assertEquals(task.getTitle(), result.getTitle());
        verify(taskRepository).findById(taskId);

    }

    // Fourth Test : I want to see if the updateTask method runs correclty
    @Test
    void updateTask_shouldReturnUpdatedTask(){

        //Arrange
        Long taskId = 1L;

        Task existingTask = new Task();
        existingTask.setTitle("Old Test Task 4");

        Task updatedTask = new Task();
        updatedTask.setTitle("New Test Task 4");

        when(taskRepository.findById(taskId))
                .thenReturn(Optional.of(existingTask));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(existingTask);
        //Act
        Task result = taskService.updateTask(taskId, updatedTask);

        //Assert
        assertEquals("New Test Task 4", result.getTitle());

        // Verify
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(existingTask);
    }

    // Fifth Test : I want to see if the updateTask method runs correclty if task not found
    @Test
    void  updateTask_shouldThrowExceptionWhenTaskNotFound(){

        //Arrange
        Long taskId = 1L;

        Task updatedTask = new Task();
        updatedTask.setTitle("New Test Task 5");

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.updateTask(taskId, updatedTask);
        });

        // Verify
        verify(taskRepository).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    // Sixth Test : I want to see if the deleteTask method runs correclty
    @Test
    void deleteTask_shouldDeleteTask(){

        //Arrange
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        //Act
        taskService.deleteTask(taskId);

        //Assert / Verify
        verify(taskRepository).existsById(taskId);
        verify(taskRepository).deleteById(taskId);

    }

    // Seventh Test : I want to see if the deleteTask method runs correclty for task not found
    @Test
    void deleteTask_shouldThrowExceptionWhenTaskNotFound(){

        //Arrange
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(false);

        //Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.deleteTask(taskId);
        });

        //Verify
        verify(taskRepository).existsById(taskId);
        verify(taskRepository, never()).deleteById(taskId);
    }



}
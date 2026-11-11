
package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;


    // First Test: Check if a task is created successfully
    @Test
    void createTask_shouldReturnCreated() throws Exception {

        // Arrange: Create a task and define the mock service response
        Task task = new Task();
        task.setTitle("Test Task");

        when(taskService.createTask(any(Task.class)))
                .thenReturn(task);

        // Act: Send a POST request to create a task
        // Assert: Check the HTTP status and response body
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Test Task"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }


    // Second Test: Check if a task is returned by its ID
    @Test
    void getTaskById_shouldReturnTask() throws Exception {

        // Arrange: Define the task ID and mock service response
        Long taskId = 1L;

        Task task = new Task();
        task.setTitle("Test Task");

        when(taskService.getATask(taskId))
                .thenReturn(task);

        // Act: Send a GET request to retrieve the task
        // Assert: Check that the response is HTTP 200 and contains the task title
        mockMvc.perform(get("/api/tasks/{id}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }


    // Third Test: Check if a task is deleted successfully
    @Test
    void deleteTask_shouldReturnNoContent() throws Exception {

        // Arrange: Define the task ID
        Long taskId = 1L;

        // Define that the mock service does nothing when deleteTask is called
        doNothing().when(taskService).deleteTask(taskId);

        // Act: Send a DELETE request
        // Assert: Check that the response is HTTP 204 No Content
        mockMvc.perform(delete("/api/tasks/{id}", taskId))
                .andExpect(status().isNoContent());

        // Verify: Check that the service delete method was called
        verify(taskService).deleteTask(taskId);
    }
}

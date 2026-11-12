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

// Load only the web layer (Controller) without starting the whole app/DB
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simulates HTTP requests

    @MockitoBean
    private TaskService taskService; // Mock the service layer

    @Test
    void createTask_shouldReturnCreated() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");

        // Tell the mock service what to return when createTask is called
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        // Send POST request with JSON body and expect 201 Created
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

    @Test
    void getTaskById_shouldReturnTask() throws Exception {
        Long taskId = 1L;
        Task task = new Task();
        task.setTitle("Test Task");

        when(taskService.getATask(taskId)).thenReturn(task);

        // Send GET request and expect 200 OK with the task title
        mockMvc.perform(get("/api/tasks/{id}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void deleteTask_shouldReturnNoContent() throws Exception {
        Long taskId = 1L;
        doNothing().when(taskService).deleteTask(taskId);

        // Send DELETE request and expect 204 No Content
        mockMvc.perform(delete("/api/tasks/{id}", taskId))
                .andExpect(status().isNoContent());

        verify(taskService).deleteTask(taskId);
    }
}
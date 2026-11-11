package com.example.taskmanagement.controller;
import com.example.taskmanagement.TaskStatus;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks") //for clear code
@RequiredArgsConstructor // Creates constructors for all final fields
public class TaskController {

    private final TaskService taskService;

    // Returns all tasks
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    // Returns a task by its ID
    @GetMapping("/{id}")
    // I want to return a ResponseEntity because I need to know HTTP Status codes!!
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getATask(id));
    }

    // Creates a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // Updates an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        return ResponseEntity.ok(taskService.updateTask(id,updatedTask));
    }

    // Deletes a task by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Searches tasks by title
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String title){
        return taskService.findTasksByTitle(title);
    }

    // Filters tasks by status
    @GetMapping("/status")
    public List<Task> filterTasks(@RequestParam TaskStatus status){
        return taskService.findTasksByStatus(status);
    }



}

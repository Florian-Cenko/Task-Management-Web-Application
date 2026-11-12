package com.example.taskmanagement.controller;
import com.example.taskmanagement.TaskStatus;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    // I want to return a ResponseEntity because I need to know HTTP Status codes!!
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getATask(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask){
        return ResponseEntity.ok(taskService.updateTask(id,updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam @NotBlank(message = "Search title cannot be blank") String title){
        return taskService.findTasksByTitle(title);
    }

    @GetMapping("/status")
    public List<Task> filterTasks(@RequestParam TaskStatus status){
        return taskService.findTasksByStatus(status);
    }



}

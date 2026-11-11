package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import com.example.taskmanagement.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Creates constructors for all final fields
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task getATask(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }


    public Task updateTask(Long id, Task updatedTask){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setTaskStatusEnum(updatedTask.getTaskStatusEnum());

        return taskRepository.save(existingTask);

    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }


    public List<Task> findTasksByTitle(String title){
        return taskRepository.findByTitle(title);
    }

    public List<Task> findTasksByStatus(TaskStatus status){
        return taskRepository.findByTaskStatusEnum(status);
    }

}

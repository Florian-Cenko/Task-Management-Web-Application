package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.Status;
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

    public Optional<Task> getATask(Long id){
            return taskRepository.findById(id);
    }


    public Optional<Task> updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id)
                             .map(existingTask -> { // I use map() to update the task only if it exists, avoiding an explicit if statement
                                 existingTask.setTitle(updatedTask.getTitle());
                                 existingTask.setDescription(updatedTask.getDescription());
                                 existingTask.setTaskStatusEnum(updatedTask.getTaskStatusEnum());

                                 return taskRepository.save(existingTask);
                             });
    }

    public boolean deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }


    public List<Task> findTasksByTitle(String title){
        return taskRepository.findByTitle(title);
    }

    public List<Task> findTasksByStatus(Status status){
        return taskRepository.findByTaskStatusEnum(status);
    }

}

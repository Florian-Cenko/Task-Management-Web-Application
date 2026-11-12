package com.example.taskmanagement.repository;

import com.example.taskmanagement.TaskStatus;
import org.hibernate.engine.spi.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagement.model.Task;

import javax.swing.*;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByTitle(String title);
    List<Task> findByTaskStatusEnum(TaskStatus status);
}

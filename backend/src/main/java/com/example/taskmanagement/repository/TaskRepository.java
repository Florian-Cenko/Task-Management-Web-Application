package com.example.taskmanagement.repository;

import org.hibernate.engine.spi.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagement.model.Task;

import javax.swing.*;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    //Spring Data JPA creates queries from names of methods
    //Find Tasks from Title
    List<Task> findByTitle(String title);

    //Filter Tasks from Status
    List<Task> findByTaskStatusEnum(Status status);
}

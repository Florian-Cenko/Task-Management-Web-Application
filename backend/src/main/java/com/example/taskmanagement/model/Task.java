package com.example.taskmanagement.model;

import com.example.taskmanagement.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

//With help of Lombok we do not need to create methods such as Getters or Setters and Constructors
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false) // also cannot be null
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatusEnum;

    @CreationTimestamp //Hibernate automatically populates the date/time upon insertion into the database
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}


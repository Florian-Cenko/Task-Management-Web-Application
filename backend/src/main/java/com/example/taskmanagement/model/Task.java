package com.example.taskmanagement.model;

import com.example.taskmanagement.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(nullable = false)
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatusEnum;

    @CreationTimestamp //Hibernate automatically populates the date/time upon insertion into the database
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}


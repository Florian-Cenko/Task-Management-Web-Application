package com.example.taskmanagement.exception;

// Custom exception for tasks that are not found
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
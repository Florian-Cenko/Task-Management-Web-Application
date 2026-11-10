package com.example.taskmanagement.service;

import com.example.taskmanagement.TaskStatus;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Λέει στο JUnit να χρησιμοποιήσει το Mockito για τα tests
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // Φτιάχνουμε ένα «ψεύτικο» (mock) Repository για να μην πειράζουμε την πραγματική βάση δεδομένων!

    @InjectMocks
    private TaskService taskService; // Βάζουμε το ψεύτικο Repository μέσα στο TaskService που θέλουμε να δοκιμάσουμε

    @Test // Αυτή η ειδοποίηση λέει στην Java: «Αυτή η μέθοδος είναι ένα τεστ!»
    void getTaskById_ShouldReturnTask_WhenTaskExists() {

        // 1. ARRANGE (Προετοιμασία)
        // Φτιάχνουμε ένα ψεύτικο task για να επιστρέψει η βάση όταν τη ρωτήσουμε
        Task mockTask = new Task(1L, "Test Task", "Description", TaskStatus.PENDING, null);

        // Λέμε στο ψεύτικο repository: «Αν σου ζητήσουν το ID 1, γύρισε αυτό το task»
        when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));


        // 2. ACT (Εκτέλεση)
        // Καλούμε τη μέθοδο του Service που θέλουμε να δοκιμάσουμε
        Optional<Task> result = taskService.getATask(1L);


        // 3. ASSERT (Επαλήθευση / Έλεγχος)
        // Ελέγχουμε αν το αποτέλεσμα είναι αυτό που περιμένουμε
        assertTrue(result.isPresent()); // Επιβεβαιώνουμε ότι βρέθηκε αποτέλεσμα
        assertEquals("Test Task", result.get().getTitle()); // Επιβεβαιώνουμε ότι ο τίτλος είναι ακριβώς "Test Task"
    }
}
package com.example.taskmanager.controller;


import com.example.taskmanager.model.task;
import com.example.taskmanager.service.taskservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class taskcontroller {

    @Autowired
    private taskservice taskService;

    // Endpoint pour créer une nouvelle tâche
    @PostMapping
    public ResponseEntity<task> createTask(@RequestBody task task) {
        task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer toutes les tâches
    @GetMapping
    public ResponseEntity<List<task>> getAllTasks() {
        List<task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Endpoint pour récupérer une tâche par ID
    @GetMapping("/{id}")
    public ResponseEntity<task> getTaskById(@PathVariable Long id) {
        task task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour mettre à jour une tâche
    @PutMapping("/{id}")
    public ResponseEntity<task> updateTask(@PathVariable Long id, @RequestBody task taskDetails) {
        task existingTask = taskService.getTaskById(id);
        if (existingTask != null) {
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setDueDate(taskDetails.getDueDate());
            task updatedTask = taskService.createTask(existingTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour supprimer une tâche
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        task task = taskService.getTaskById(id);
        if (task != null) {
            taskService.deletetask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

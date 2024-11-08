package com.example.taskmanager.service;

import com.example.taskmanager.model.task;
import com.example.taskmanager.taskrepository.taskrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;


@Service
public class taskservice {

    @Autowired
    private taskrepository taskRepository;

    // Crée une nouvelle tâche
    public task createTask(task task) {
        task.setCreationDate(new Date());
        return taskRepository.save(task);
    }

    // Récupère toutes les tâches
    public List<task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Récupère une tâche par son ID
    public task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    public void deletetask(Long id) {
        taskRepository.deleteById(id);
    }
    
}

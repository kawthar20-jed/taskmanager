package com.example.taskmanager.taskrepository;


import com.example.taskmanager.model.task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface taskrepository extends JpaRepository<task, Long> {
}


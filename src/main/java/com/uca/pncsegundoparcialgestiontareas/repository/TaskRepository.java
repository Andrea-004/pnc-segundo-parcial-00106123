package com.uca.pncsegundoparcialgestiontareas.repository;

import com.uca.pncsegundoparcialgestiontareas.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
        boolean existsByTitle(String title);
        List<Task> findByStatus(String status);
        List<Task> findByPriority(String priority);
}

package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}

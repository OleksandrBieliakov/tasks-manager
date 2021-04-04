package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Assignment;
import com.obieliakov.tasksmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("select a.assignedTo from Assignment a where a.task = :task")
    List<AppUser> queryAssignedAppUsersByTask(@Param("task") Task task);
}

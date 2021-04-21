package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("select a from Assignment a where a.task.id = :task_id and a.assignedTo.id = :assigned_to_app_user_id")
    Optional<Assignment> queryByTaskIdAndAssignedToAppUserId(@Param("task_id") Long groupId, @Param("assigned_to_app_user_id") UUID appUserId);
}

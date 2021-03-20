package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Assignment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {
}

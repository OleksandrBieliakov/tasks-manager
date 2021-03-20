package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}

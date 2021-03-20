package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
}

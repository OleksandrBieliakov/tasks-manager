package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}

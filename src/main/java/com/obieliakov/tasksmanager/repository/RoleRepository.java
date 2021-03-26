package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

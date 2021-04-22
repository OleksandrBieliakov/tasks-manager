package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByGroupAndTitle(Group group, String title);
}

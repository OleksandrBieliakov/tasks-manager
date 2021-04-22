package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByGroupAndTitle(Group group, String title);

    @Query("select r.groupMemberships.size from Role r where r=:role")
    Long queryNumberOfAppUsersWithRole(@Param("role") Role role);
}

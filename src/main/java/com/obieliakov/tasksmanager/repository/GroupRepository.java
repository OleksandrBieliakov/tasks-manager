package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);
}

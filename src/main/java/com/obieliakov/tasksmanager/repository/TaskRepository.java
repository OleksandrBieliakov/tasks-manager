package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByGroup(Group group);
}

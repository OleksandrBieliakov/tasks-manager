package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.StatusUpdate;
import com.obieliakov.tasksmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusUpdateRepository extends JpaRepository<StatusUpdate, Long> {

    List<StatusUpdate> findAllByTask(Task task);
}

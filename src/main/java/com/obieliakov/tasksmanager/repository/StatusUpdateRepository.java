package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.StatusUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusUpdateRepository extends JpaRepository<StatusUpdate, Long> {
}

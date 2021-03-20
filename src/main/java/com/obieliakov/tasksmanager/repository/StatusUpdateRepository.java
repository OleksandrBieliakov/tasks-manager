package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.StatusUpdate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusUpdateRepository extends PagingAndSortingRepository<StatusUpdate, Long> {
}

package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.GroupInvite;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupInviteRepository extends PagingAndSortingRepository<GroupInvite, Long> {
}

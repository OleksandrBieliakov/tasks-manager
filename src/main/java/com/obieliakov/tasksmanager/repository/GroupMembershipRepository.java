package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.model.GroupMembership;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupMembershipRepository extends PagingAndSortingRepository<GroupMembership, Long> {
}

package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.model.GroupMembership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupMembershipRepository extends PagingAndSortingRepository<GroupMembership, Long> {

    @Query("select new com.obieliakov.tasksmanager.dto.AppUserShortDto(u.id, u.loginName, u.firstName, u.lastName) " +
            "from AppUser u join GroupMembership gm on gm.user = u where gm.group.id = :group_id")
    List<AppUserShortDto> queryAppUsersShortByGroupId(@Param("group_id") Long groupId);
}

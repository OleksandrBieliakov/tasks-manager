package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // practice of custom query with Projection
    @Query("select new com.obieliakov.tasksmanager.dto.AppUserShortDto(u.id, u.loginName, u.firstName, u.lastName) " +
            "from GroupMembership gm join gm.user u where gm.group.id = :group_id")
    List<AppUserShortDto> queryAppUsersShortMembersOfGroupWithId(@Param("group_id") Long groupId);

    @Query("select gm.user from GroupMembership gm where gm.group.id = :group_id")
    List<AppUser> queryAppUsersWithMembershipInGroupWithId(@Param("group_id") Long groupId);
}

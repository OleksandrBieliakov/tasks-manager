package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {

    @Query("select gm.appUser from GroupMembership gm where gm.group.id = :group_id and gm.active=true")
    List<AppUser> queryActiveMembersOfGroupWithId(@Param("group_id") Long groupId);

    // added to practice custom hql query creation with projection to AppUserShortDto
    @Query("select new com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto(u.id, u.loginName, u.firstName, u.lastName) " +
            "from GroupMembership gm join gm.appUser u where gm.group.id = :group_id and gm.active=true")
    List<AppUserShortDto> queryActiveMembersShortOfGroupWithId(@Param("group_id") Long groupId);

    Optional<GroupMembership> findByGroupAndAppUser(Group group, AppUser appUser);

    @Query("select gm from GroupMembership gm where gm.group.id = :group_id and gm.appUser.id = :app_user_id and gm.active=true")
    Optional<GroupMembership> queryByGroupIdAndAppUserIdAndActiveTrue(@Param("group_id") Long groupId, @Param("app_user_id")UUID appUserId);

    @Query("select gm.group from GroupMembership gm where gm.appUser.id = :app_user_id and gm.active=true")
    List<Group> queryGroupsWithAppUserActiveMembership(@Param("app_user_id") UUID appUserId);
}

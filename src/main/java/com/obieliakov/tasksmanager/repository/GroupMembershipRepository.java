package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {

    // members - added to practice custom hql query creation
    @Query("select gm.user from GroupMembership gm where gm.group.id = :group_id")
    List<AppUser> queryMembersOfGroupWithId(@Param("group_id") Long groupId);

    // members - added to practice custom hql query creation with projection to AppUserShortDto
    @Query("select new com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto(u.id, u.loginName, u.firstName, u.lastName) " +
            "from GroupMembership gm join gm.user u where gm.group.id = :group_id")
    List<AppUserShortDto> queryMembersShortOfGroupWithId(@Param("group_id") Long groupId);

    List<GroupMembership> findGroupMembershipByActiveTrueAndGroup(Group group);
}

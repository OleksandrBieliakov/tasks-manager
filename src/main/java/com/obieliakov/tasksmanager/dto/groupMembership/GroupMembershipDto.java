package com.obieliakov.tasksmanager.dto.groupMembership;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.role.RoleShortDto;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class GroupMembershipDto {

    private Long id;
    private GroupInfoDto group;
    private AppUserDto appUser;
    private List<RoleShortDto> roles;
    private boolean active;
    private ZonedDateTime firstTimeJoined;
    private ZonedDateTime lastTimeJoined;
    private ZonedDateTime lastTimeLeft;
}

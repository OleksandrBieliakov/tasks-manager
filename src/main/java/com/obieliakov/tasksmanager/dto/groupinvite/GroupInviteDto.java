package com.obieliakov.tasksmanager.dto.groupinvite;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GroupInviteDto {

    private Long id;
    private ZonedDateTime timeCreated;
    private GroupInfoDto group;
    private AppUserDto byAppUser;
    private AppUserDto toAppUser;
}

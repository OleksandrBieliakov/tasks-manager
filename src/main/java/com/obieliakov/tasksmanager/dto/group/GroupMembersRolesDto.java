package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.dto.appUser.AppUserRolesDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupMembersRolesDto {

    private Long id;
    private String name;
    private List<AppUserRolesDto> members;
}

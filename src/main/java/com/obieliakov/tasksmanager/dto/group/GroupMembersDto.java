package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupMembersDto {

    private Long id;
    private String name;
    private List<AppUserDto> members;
}

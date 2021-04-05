package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupMembersShortDto {

    private Long id;
    private String name;
    private List<AppUserShortDto> members;
}

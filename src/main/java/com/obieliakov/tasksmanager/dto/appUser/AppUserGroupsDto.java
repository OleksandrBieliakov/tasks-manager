package com.obieliakov.tasksmanager.dto.appUser;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AppUserGroupsDto {

    private UUID id;
    private List<GroupInfoDto> groupInfoDtoList;
}

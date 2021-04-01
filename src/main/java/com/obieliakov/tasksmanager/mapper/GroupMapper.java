package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersShortDto;
import com.obieliakov.tasksmanager.dto.group.NewOrUpdateGroupDto;
import com.obieliakov.tasksmanager.model.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupInfoDto groupToGroupInfoDto(Group group);

    GroupMembersDto groupToGroupMembersDto(Group group);

    GroupMembersShortDto groupToGroupMembersShortDto(Group group);

    Group newOrUpdateGroupDtoToGroup(NewOrUpdateGroupDto newOrUpdateGroupDto);
}

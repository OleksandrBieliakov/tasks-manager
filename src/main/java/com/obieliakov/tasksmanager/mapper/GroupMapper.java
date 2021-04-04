package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupInfoDto groupToGroupInfoDto(Group group);

    GroupMembersDto groupToGroupMembersDto(Group group);

    GroupMembersShortDto groupToGroupMembersShortDto(Group group);

    Group newOrUpdateGroupDtoToGroup(NewOrUpdateGroupDto newOrUpdateGroupDto);

    Group copyNewOrUpdateGroupDtoToGroup(NewOrUpdateGroupDto newOrUpdateGroupDto, @MappingTarget Group group);

    @Mapping(target = "tasks", ignore = true)
    GroupTasksDto groupToGroupTasksDto(Group group);
}

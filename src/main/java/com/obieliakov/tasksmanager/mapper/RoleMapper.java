package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.role.RoleDto;
import com.obieliakov.tasksmanager.dto.role.RoleShortDto;
import com.obieliakov.tasksmanager.model.Role;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto roleToRoleDto(Role role);

    RoleShortDto roleToRoleShortDto(Role role);

    List<RoleShortDto> roleListToRoleShortDtoList(Set<Role> roleList);
}

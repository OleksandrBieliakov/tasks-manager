package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.role.RoleDto;
import com.obieliakov.tasksmanager.dto.role.RoleShortDto;
import com.obieliakov.tasksmanager.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto roleToRoleDto(Role role);

    RoleShortDto roleToRoleShortDto(Role role);
}

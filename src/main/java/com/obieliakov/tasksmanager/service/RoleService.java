package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.role.NewRoleDto;
import com.obieliakov.tasksmanager.dto.role.RoleAssignmentDto;
import com.obieliakov.tasksmanager.dto.role.RoleDto;
import com.obieliakov.tasksmanager.dto.role.UpdateRoleDto;
import com.obieliakov.tasksmanager.model.Role;

import java.util.UUID;

public interface RoleService {

    Role roleModelById(Long id);

    RoleDto roleById(Long id);

    RoleDto createRole(NewRoleDto newRoleDto);

    RoleDto updateRole(Long id, UpdateRoleDto updateRoleDto);

    void deleteRole(Long id);

    RoleAssignmentDto assignRole(Long id, UUID appUserId);

    void unassignRole(Long id, UUID appUserId);
}

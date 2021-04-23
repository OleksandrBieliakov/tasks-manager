package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.role.NewRoleDto;
import com.obieliakov.tasksmanager.dto.role.RoleAssignmentDto;
import com.obieliakov.tasksmanager.dto.role.RoleDto;
import com.obieliakov.tasksmanager.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/roles")
@SecurityRequirement(name = "identity")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{id}")
    public RoleDto roleById(@PathVariable Long id) {
        return roleService.roleById(id);
    }

    @PostMapping
    public RoleDto createRole(@RequestBody NewRoleDto newRoleDto) {
        return roleService.createRole(newRoleDto);
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    @PatchMapping("{id}/assign")
    public RoleAssignmentDto assignRole(@PathVariable Long id) {
        return roleService.assignRole(id);
    }

    @PatchMapping("{id}/unassign")
    public void unassignRole(@PathVariable Long id) {
        roleService.unassignRole(id);
    }
}

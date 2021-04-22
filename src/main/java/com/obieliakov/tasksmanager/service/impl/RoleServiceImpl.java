package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.role.NewRoleDto;
import com.obieliakov.tasksmanager.dto.role.RoleAssignmentDto;
import com.obieliakov.tasksmanager.dto.role.RoleDto;
import com.obieliakov.tasksmanager.mapper.RoleMapper;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Role;
import com.obieliakov.tasksmanager.repository.RoleRepository;
import com.obieliakov.tasksmanager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final Validator validator;

    private final RoleMapper roleMapper;

    private final RoleRepository roleRepository;

    private final IdentityService identityService;
    private final GroupMembershipService groupMembershipService;
    private final AppUserService appUserService;
    private final GroupService groupService;

    public RoleServiceImpl(Validator validator, RoleMapper roleMapper, RoleRepository roleRepository, IdentityService identityService, GroupMembershipService groupMembershipService, AppUserService appUserService, GroupService groupService) {
        this.validator = validator;
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.identityService = identityService;
        this.groupMembershipService = groupMembershipService;
        this.appUserService = appUserService;
        this.groupService = groupService;
    }

    private void verifyRoleTitleAvailability(Group group, String title) {
        if(roleRepository.findByGroupAndTitle(group, title).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role with such title already exists");
        }
    }

    @Override
    public Role roleModelById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }
        return role.get();
    }

    @Override
    public RoleDto roleById(Long id) {
        Role role = roleModelById(id);
        groupMembershipService.verifyCurrentUserMembership(role.getGroup().getId());
        return roleMapper.roleToRoleDto(role);
    }

    @Override
    public RoleDto createRole(NewRoleDto newRoleDto) {
        newRoleDto.trim();

        Set<ConstraintViolation<NewRoleDto>> violations = validator.validate(newRoleDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        groupMembershipService.verifyCurrentUserMembership(newRoleDto.getGroupId());

        Group group = groupService.groupModelById(newRoleDto.getGroupId());

        verifyRoleTitleAvailability(group, newRoleDto.getTitle());

        Role newRole = new Role();
        newRole.setGroup(group);
        newRole.setTitle(newRoleDto.getTitle());

        Role createdRole = roleRepository.save(newRole);
        return roleMapper.roleToRoleDto(createdRole);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleModelById(id);

        Group group = role.getGroup();
        groupMembershipService.verifyCurrentUserMembership(group.getId());

        if(roleRepository.queryNumberOfAppUsersWithRole(role) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role is assigned");
        }

        roleRepository.delete(role);
    }

    @Override
    public RoleAssignmentDto assignRole(Long id, UUID appUserId) {
        return null;
    }

    @Override
    public void unassignRole(Long id, UUID appUserId) {

    }
}

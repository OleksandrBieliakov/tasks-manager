package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.role.NewRoleDto;
import com.obieliakov.tasksmanager.dto.role.RoleAssignmentDto;
import com.obieliakov.tasksmanager.dto.role.RoleDto;
import com.obieliakov.tasksmanager.dto.role.UpdateRoleDto;
import com.obieliakov.tasksmanager.mapper.RoleMapper;
import com.obieliakov.tasksmanager.model.Role;
import com.obieliakov.tasksmanager.repository.RoleRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import com.obieliakov.tasksmanager.service.GroupMembershipService;
import com.obieliakov.tasksmanager.service.IdentityService;
import com.obieliakov.tasksmanager.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final Validator validator;

    private final RoleMapper roleMapper;

    private final RoleRepository repository;

    private final IdentityService identityService;
    private final GroupMembershipService groupMembershipService;
    private final AppUserService appUserService;

    public RoleServiceImpl(Validator validator, RoleMapper roleMapper, RoleRepository repository, IdentityService identityService, GroupMembershipService groupMembershipService, AppUserService appUserService) {
        this.validator = validator;
        this.roleMapper = roleMapper;
        this.repository = repository;
        this.identityService = identityService;
        this.groupMembershipService = groupMembershipService;
        this.appUserService = appUserService;
    }

    @Override
    public Role roleModelById(Long id) {
        return null;
    }

    @Override
    public RoleDto roleById(Long id) {
        return null;
    }

    @Override
    public RoleDto createRole(NewRoleDto newRoleDto) {
        return null;
    }

    @Override
    public RoleDto updateRole(Long id, UpdateRoleDto updateRoleDto) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public RoleAssignmentDto assignRole(Long id, UUID appUserId) {
        return null;
    }

    @Override
    public void unassignRole(Long id, UUID appUserId) {

    }
}

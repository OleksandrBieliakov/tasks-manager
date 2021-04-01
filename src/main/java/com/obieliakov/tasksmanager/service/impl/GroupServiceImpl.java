package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersShortDto;
import com.obieliakov.tasksmanager.dto.group.NewOrUpdateGroupDto;
import com.obieliakov.tasksmanager.mapper.AppUserMapper;
import com.obieliakov.tasksmanager.mapper.GroupMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.repository.GroupMembershipRepository;
import com.obieliakov.tasksmanager.repository.GroupRepository;
import com.obieliakov.tasksmanager.repository.TaskRepository;
import com.obieliakov.tasksmanager.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Validated
@Transactional
public class GroupServiceImpl implements GroupService {

    private final Validator validator;

    private final AppUserMapper appUserMapper;
    private final GroupMapper groupMapper;

    private final GroupRepository groupRepository;
    private final AppUserRepository appUserRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final TaskRepository taskRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, Validator validator, GroupMembershipRepository groupMembershipRepository, AppUserMapper appUserMapper, AppUserRepository appUserRepository, TaskRepository taskRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.validator = validator;
        this.groupMembershipRepository = groupMembershipRepository;
        this.appUserMapper = appUserMapper;
        this.appUserRepository = appUserRepository;
        this.taskRepository = taskRepository;
    }

    private Group groupModelById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
        return group.get();
    }

    @Override
    public GroupInfoDto groupInfoById(Long id) {
        Group group = groupModelById(id);
        return groupMapper.groupToGroupInfoDto(group);
    }

    private void trimAndValidateNewOrUpdateGroupDto(NewOrUpdateGroupDto newOrUpdateGroupDto) {
        newOrUpdateGroupDto.trim();

        Set<ConstraintViolation<NewOrUpdateGroupDto>> violations = validator.validate(newOrUpdateGroupDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Override
    public GroupInfoDto createGroup(NewOrUpdateGroupDto newOrUpdateGroupDto) {
        trimAndValidateNewOrUpdateGroupDto(newOrUpdateGroupDto);

        Group newGroup = groupMapper.newOrUpdateGroupDtoToGroup(newOrUpdateGroupDto);

        Group createdGroup = groupRepository.save(newGroup);
        return groupMapper.groupToGroupInfoDto(createdGroup);
    }

    @Override
    public GroupInfoDto updateGroupInfo(Long id, NewOrUpdateGroupDto newOrUpdateGroupDto) {
        trimAndValidateNewOrUpdateGroupDto(newOrUpdateGroupDto);

        Group existingGroup = groupModelById(id);

        existingGroup.setName(newOrUpdateGroupDto.getName());

        Group updatedGroup = groupRepository.save(existingGroup);
        return groupMapper.groupToGroupInfoDto(updatedGroup);
    }

    // members - without custom querying
    @Override
    public GroupMembersDto groupMembersById(Long id) {
        Group group = groupModelById(id);

        List<AppUser> appUserList = new ArrayList<>();
        List<GroupMembership> memberships = groupMembershipRepository.findGroupMembershipByActiveTrueAndGroup(group);
        for (GroupMembership membership : memberships) {
            appUserList.add(appUserRepository.findByGroupMembershipsContains(membership));
        }

        List<AppUserDto> members = appUserMapper.appUserListToAppUserDtoList(appUserList);

        GroupMembersDto groupMembersDto = groupMapper.groupToGroupMembersDto(group);
        groupMembersDto.setMembers(members);
        return groupMembersDto;
    }

    // members - added to practice custom hql query creation
    @Override
    public GroupMembersDto groupMembersCustomById(Long id) {
        Group group = groupModelById(id);

        List<AppUser> appUserList = groupMembershipRepository.queryMembersOfGroupWithId(id);

        List<AppUserDto> members = appUserMapper.appUserListToAppUserDtoList(appUserList);

        GroupMembersDto groupMembersDto = groupMapper.groupToGroupMembersDto(group);
        groupMembersDto.setMembers(members);
        return groupMembersDto;
    }

    // members - added to practice custom hql query creation with projection to AppUserShortDto
    @Override
    public GroupMembersShortDto groupMembersCustomShortById(Long id) {
        Group group = groupModelById(id);

        List<AppUserShortDto> members = groupMembershipRepository.queryMembersShortOfGroupWithId(id);

        GroupMembersShortDto groupMembersShortDto = groupMapper.groupToGroupMembersShortDto(group);
        groupMembersShortDto.setMembers(members);
        return groupMembersShortDto;
    }
}

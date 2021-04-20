package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.dto.groupinvite.GroupInviteDto;
import com.obieliakov.tasksmanager.dto.groupinvite.NewGroupInviteDto;
import com.obieliakov.tasksmanager.dto.task.TaskAssignedToDto;
import com.obieliakov.tasksmanager.mapper.*;
import com.obieliakov.tasksmanager.model.*;
import com.obieliakov.tasksmanager.repository.GroupInviteRepository;
import com.obieliakov.tasksmanager.repository.GroupMembershipRepository;
import com.obieliakov.tasksmanager.repository.GroupRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import com.obieliakov.tasksmanager.service.GroupMembershipService;
import com.obieliakov.tasksmanager.service.GroupService;
import com.obieliakov.tasksmanager.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final Validator validator;

    private final AppUserMapper appUserMapper;
    private final AppUserWithPrivacyMapper appUserWithPrivacyMapper;
    private final GroupMapper groupMapper;
    private final TaskMapper taskMapper;
    private final GroupInviteMapper groupInviteMapper;

    private final GroupRepository groupRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final GroupInviteRepository groupInviteRepository;

    private final IdentityService identityService;
    private final GroupMembershipService groupMembershipService;
    private final AppUserService appUserService;

    public GroupServiceImpl(Validator validator, AppUserMapper appUserMapper, AppUserWithPrivacyMapper appUserWithPrivacyMapper, GroupMapper groupMapper, TaskMapper taskMapper, GroupInviteMapper groupInviteMapper, GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository, GroupInviteRepository groupInviteRepository, IdentityService identityService, GroupMembershipService groupMembershipService, AppUserService appUserService) {
        this.validator = validator;
        this.appUserMapper = appUserMapper;
        this.appUserWithPrivacyMapper = appUserWithPrivacyMapper;
        this.groupMapper = groupMapper;
        this.taskMapper = taskMapper;
        this.groupInviteMapper = groupInviteMapper;
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.groupInviteRepository = groupInviteRepository;
        this.identityService = identityService;
        this.groupMembershipService = groupMembershipService;
        this.appUserService = appUserService;
    }

    private void trimAndValidateNewOrUpdateGroupDto(NewOrUpdateGroupDto newOrUpdateGroupDto) {
        newOrUpdateGroupDto.trim();

        Set<ConstraintViolation<NewOrUpdateGroupDto>> violations = validator.validate(newOrUpdateGroupDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Override
    public Group groupModelById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
        return group.get();
    }

    @Override
    public GroupInfoDto groupInfoById(Long id, boolean isAdmin) {
        if (!isAdmin) {
            groupMembershipService.verifyCurrentUserMembership(id);
        }
        Group group = groupModelById(id);
        return groupMapper.groupToGroupInfoDto(group);
    }

    @Override
    public GroupInfoDto createGroup(NewOrUpdateGroupDto newOrUpdateGroupDto) {
        trimAndValidateNewOrUpdateGroupDto(newOrUpdateGroupDto);

        Group newGroup = groupMapper.newOrUpdateGroupDtoToGroup(newOrUpdateGroupDto);

        Group createdGroup = groupRepository.save(newGroup);

        UUID currentAppUserId = identityService.currentUserID();
        AppUser currentAppUser = appUserService.appUserModelById(currentAppUserId);

        GroupMembership groupMembership = new GroupMembership();
        groupMembership.setGroup(createdGroup);
        groupMembership.setAppUser(currentAppUser);

        groupMembershipRepository.save(groupMembership);

        return groupMapper.groupToGroupInfoDto(createdGroup);
    }

    @Override
    public GroupInfoDto updateGroupInfo(Long id, NewOrUpdateGroupDto newOrUpdateGroupDto) {
        trimAndValidateNewOrUpdateGroupDto(newOrUpdateGroupDto);

        groupMembershipService.verifyCurrentUserMembership(id);

        Group existingGroup = groupModelById(id);

        existingGroup = groupMapper.copyNewOrUpdateGroupDtoToGroup(newOrUpdateGroupDto, existingGroup);

        Group updatedGroup = groupRepository.save(existingGroup);
        return groupMapper.groupToGroupInfoDto(updatedGroup);
    }

    @Override
    public GroupMembersDto groupMembersById(Long id, boolean isAdmin) {
        if (!isAdmin) {
            groupMembershipService.verifyCurrentUserMembership(id);
        }

        Group group = groupModelById(id);

        List<AppUser> appUserList = groupMembershipRepository.queryActiveMembersOfGroupWithId(id);

        List<AppUserDto> appUserDtoList = isAdmin ?
                appUserMapper.appUserListToAppUserDtoList(appUserList) :
                appUserWithPrivacyMapper.appUserListToAppUserDtoListWithPrivacy(appUserList);

        GroupMembersDto groupMembersDto = groupMapper.groupToGroupMembersDto(group);
        groupMembersDto.setMembers(appUserDtoList);
        return groupMembersDto;
    }

    @Override
    public GroupTasksDto groupTasksById(Long id, boolean isAdmin) {
        if (!isAdmin) {
            groupMembershipService.verifyCurrentUserMembership(id);
        }

        Group group = groupModelById(id);

        List<Task> taskList = group.getTasks();

        List<TaskAssignedToDto> taskAssignedToDtoList = taskList.stream().map(task -> {
            TaskAssignedToDto taskAssignedToDto = taskMapper.taskToTaskAssignedToDto(task);

            List<AppUser> assignedAppUsers = task.getAssignments().stream()
                    .map(Assignment::getAssignedTo).collect(Collectors.toList());

            List<AppUserDto> appUserDtoList = isAdmin ?
                    appUserMapper.appUserListToAppUserDtoList(assignedAppUsers) :
                    appUserWithPrivacyMapper.appUserListToAppUserDtoListWithPrivacy(assignedAppUsers);

            taskAssignedToDto.setAssignedTo(appUserDtoList);

            return taskAssignedToDto;
        }).collect(Collectors.toList());

        GroupTasksDto groupTasksDto = groupMapper.groupToGroupTasksDto(group);
        groupTasksDto.setTasks(taskAssignedToDtoList);
        return groupTasksDto;
    }

    @Override
    public GroupInviteDto createGroupInvite(NewGroupInviteDto newGroupInviteDto) {
        Set<ConstraintViolation<NewGroupInviteDto>> violations = validator.validate(newGroupInviteDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Long groupId = newGroupInviteDto.getGroupId();
        UUID invitedAppUserId = newGroupInviteDto.getInvitedAppUserId();
        UUID currentAppUserId = identityService.currentUserID();

        groupMembershipService.verifyMembership(currentAppUserId, groupId);

        if(groupMembershipService.isAppUserMemberOfGroup(invitedAppUserId, groupId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invited user is already a group member");
        }

        Group group = groupModelById(groupId);
        AppUser invitedAppUser = appUserService.appUserModelById(invitedAppUserId);
        AppUser currentAppUSer = appUserService.appUserModelById(currentAppUserId);

        Optional<GroupInvite> existingGroupInvite = groupInviteRepository.findByGroupAndAndToAppUserAndByAppUser(group, invitedAppUser, currentAppUSer);
        if(existingGroupInvite.isPresent()) {
            return groupInviteMapper.groupInviteToGroupInviteDto(existingGroupInvite.get());
        }

        GroupInvite newGroupInvite = new GroupInvite();
        newGroupInvite.setGroup(group);
        newGroupInvite.setToAppUser(invitedAppUser);
        newGroupInvite.setByAppUser(currentAppUSer);

        GroupInvite createdGroupInvite = groupInviteRepository.save(newGroupInvite);
        return groupInviteMapper.groupInviteToGroupInviteDto(createdGroupInvite);
    }

    @Override
    public List<GroupInfoDto> allGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.groupListToGroupInfoDtoList(groups);
    }

    // added to practice custom hql query creation with projection to AppUserShortDto
    // used by admin only
    @Override
    public GroupMembersShortDto groupMembersShortById(Long id) {
        Group group = groupModelById(id);

        List<AppUserShortDto> appUserShortDtoList = groupMembershipRepository.queryActiveMembersShortOfGroupWithId(id);

        GroupMembersShortDto groupMembersShortDto = groupMapper.groupToGroupMembersShortDto(group);
        groupMembersShortDto.setMembers(appUserShortDtoList);
        return groupMembersShortDto;
    }
}

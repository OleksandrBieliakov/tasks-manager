package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.dto.task.TaskInfoDto;
import com.obieliakov.tasksmanager.mapper.AppUserMapper;
import com.obieliakov.tasksmanager.mapper.GroupMapper;
import com.obieliakov.tasksmanager.mapper.TaskMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.repository.AssignmentRepository;
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
    private final TaskMapper taskMapper;

    private final GroupRepository groupRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final TaskRepository taskRepository;
    private final AssignmentRepository assignmentRepository;

    public GroupServiceImpl(Validator validator, AppUserMapper appUserMapper, GroupMapper groupMapper, TaskMapper taskMapper, GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository, TaskRepository taskRepository, AssignmentRepository assignmentRepository) {
        this.validator = validator;
        this.appUserMapper = appUserMapper;
        this.groupMapper = groupMapper;
        this.taskMapper = taskMapper;
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.taskRepository = taskRepository;
        this.assignmentRepository = assignmentRepository;
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

        existingGroup = groupMapper.copyNewOrUpdateGroupDtoToGroup(newOrUpdateGroupDto, existingGroup);

        Group updatedGroup = groupRepository.save(existingGroup);
        return groupMapper.groupToGroupInfoDto(updatedGroup);
    }

    @Override
    public GroupMembersDto groupMembersById(Long id) {
        Group group = groupModelById(id);

        List<AppUser> appUserList = groupMembershipRepository.queryMembersOfGroupWithId(id);

        List<AppUserDto> appUserDtoList = appUserMapper.appUserListToAppUserDtoList(appUserList);

        GroupMembersDto groupMembersDto = groupMapper.groupToGroupMembersDto(group);
        groupMembersDto.setMembers(appUserDtoList);
        return groupMembersDto;
    }

    // added to practice custom hql query creation with projection to AppUserShortDto
    @Override
    public GroupMembersShortDto groupMembersShortById(Long id) {
        Group group = groupModelById(id);

        List<AppUserShortDto> appUserShortDtoList = groupMembershipRepository.queryMembersShortOfGroupWithId(id);

        GroupMembersShortDto groupMembersShortDto = groupMapper.groupToGroupMembersShortDto(group);
        groupMembersShortDto.setMembers(appUserShortDtoList);
        return groupMembersShortDto;
    }

    @Override
    public GroupTasksDto groupTasksById(Long id) {
        Group group = groupModelById(id);

        List<Task> taskList = taskRepository.findByGroup(group);

        List<TaskInfoDto> taskInfoDtoList = new ArrayList<>();

        for(Task task: taskList) {
            TaskInfoDto taskInfoDto = taskMapper.taskToTaskInfoDto(task);

            List<AppUser> assignedAppUsers = assignmentRepository.queryAssignedAppUsersByTask(task);

            List<AppUserDto> appUserDtoList = appUserMapper.appUserListToAppUserDtoList(assignedAppUsers);
            taskInfoDto.setAssignedTo(appUserDtoList);

            taskInfoDtoList.add(taskInfoDto);
        }

        GroupTasksDto groupTasksDto = groupMapper.groupToGroupTasksDto(group);
        groupTasksDto.setTasks(taskInfoDtoList);
        return groupTasksDto;
    }
}

package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.task.NewTaskCreatedDto;
import com.obieliakov.tasksmanager.dto.task.NewTaskDto;
import com.obieliakov.tasksmanager.mapper.TaskMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.repository.GroupMembershipRepository;
import com.obieliakov.tasksmanager.repository.GroupRepository;
import com.obieliakov.tasksmanager.repository.TaskRepository;
import com.obieliakov.tasksmanager.service.TaskService;
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

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final Validator validator;

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final GroupRepository groupRepository;
    private final GroupMembershipRepository groupMembershipRepository;


    public TaskServiceImpl(Validator validator, TaskMapper taskMapper, TaskRepository taskRepository, AppUserRepository appUserRepository, GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository) {
        this.validator = validator;
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
    }

    private AppUser appUserModelById(Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUser.get();
    }

    private Group groupModelById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
        return group.get();
    }

    private GroupMembership activeGroupMembershipByGroupAndAppUser(Group group, AppUser appUser) {
        Optional<GroupMembership> groupMembership = groupMembershipRepository.findByGroupAndAppUserAndActiveTrue(group, appUser);
        if (groupMembership.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User in not a an active member of Group");
        }
        return groupMembership.get();
    }

    @Override
    public NewTaskCreatedDto createTask(NewTaskDto newTaskDto) {
        newTaskDto.trim();

        Set<ConstraintViolation<NewTaskDto>> violations = validator.validate(newTaskDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        AppUser addedBy = appUserModelById(newTaskDto.getAddedByAppUserId());
        Group group = groupModelById(newTaskDto.getGroupId());
        GroupMembership groupMembership = activeGroupMembershipByGroupAndAppUser(group, addedBy);

        Task newTask = taskMapper.newTaskDtoToTask(newTaskDto);
        newTask.setAddedBy(addedBy);
        newTask.setGroup(group);
        Task createdTask = taskRepository.save(newTask);
        return taskMapper.taskToNewTaskCreatedDto(createdTask);
    }
}

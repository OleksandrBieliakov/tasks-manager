package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.statusupdate.NewStatusUpdateDto;
import com.obieliakov.tasksmanager.dto.statusupdate.StatusUpdateDto;
import com.obieliakov.tasksmanager.dto.task.*;
import com.obieliakov.tasksmanager.mapper.StatusUpdateMapper;
import com.obieliakov.tasksmanager.mapper.TaskMapper;
import com.obieliakov.tasksmanager.model.*;
import com.obieliakov.tasksmanager.repository.*;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final Validator validator;

    private final TaskMapper taskMapper;
    private final StatusUpdateMapper statusUpdateMapper;

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final GroupRepository groupRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final StatusUpdateRepository statusUpdateRepository;


    public TaskServiceImpl(Validator validator, TaskMapper taskMapper, StatusUpdateMapper statusUpdateMapper, TaskRepository taskRepository, AppUserRepository appUserRepository, GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository, StatusUpdateRepository statusUpdateRepository) {
        this.validator = validator;
        this.taskMapper = taskMapper;
        this.statusUpdateMapper = statusUpdateMapper;
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.statusUpdateRepository = statusUpdateRepository;
    }

    private Task taskModelById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return task.get();
    }

    private AppUser appUserModelById(UUID id) {
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

    @Override
    public TaskUpdatedDto updateTaskInfo(Long id, UpdateTaskInfoDto updateTaskInfoDto) {
        updateTaskInfoDto.trim();

        Set<ConstraintViolation<UpdateTaskInfoDto>> violations = validator.validate(updateTaskInfoDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Task existingTask = taskModelById(id);

        existingTask = taskMapper.copyUpdateTaskInfoDtoToTask(updateTaskInfoDto, existingTask);

        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.taskToTaskUpdatedDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskModelById(id);
        taskRepository.delete(task);
    }

    @Override
    public StatusUpdateDto updateTaskStatus(Long id, NewStatusUpdateDto newStatusUpdateDto) {
        Set<ConstraintViolation<NewStatusUpdateDto>> violations = validator.validate(newStatusUpdateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Task task = taskModelById(id);
        AppUser updatedBy = appUserModelById(newStatusUpdateDto.getUpdatedByAppUserId());
        Group group = task.getGroup();
        GroupMembership groupMembership = activeGroupMembershipByGroupAndAppUser(group, updatedBy);

        if(task.getStatus().equals(newStatusUpdateDto.getNewTaskStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task already has this status");
        }

        StatusUpdate newStatusUpdate = new StatusUpdate();
        newStatusUpdate.setStatus(newStatusUpdateDto.getNewTaskStatus());
        newStatusUpdate.setTask(task);
        newStatusUpdate.setUpdatedBy(updatedBy);

        StatusUpdate createdStatusUpdate = statusUpdateRepository.save(newStatusUpdate);

        task.setStatus(newStatusUpdateDto.getNewTaskStatus());
        taskRepository.save(task);

        return statusUpdateMapper.statusUpdateToStatusUpdateDto(createdStatusUpdate);
    }

    @Override
    public TaskStatusUpdatesDto taskStatusUpdates(Long id) {
        Task task = taskModelById(id);

        List<StatusUpdate> statusUpdateList = statusUpdateRepository.findAllByTask(task);

        List<StatusUpdateDto> statusUpdateDtoList = statusUpdateMapper.statusUpdateListToStatusUpdateDtoList(statusUpdateList);

        TaskStatusUpdatesDto taskStatusUpdatesDto = taskMapper.taskToTaskStatusUpdatesDto(task);
        taskStatusUpdatesDto.setStatusUpdates(statusUpdateDtoList);
        return taskStatusUpdatesDto;
    }

    @Override
    public List<TaskShortInfoDto> allTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskMapper.taskListToTaskShortInfoDtoList(taskList);
    }
}

package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.statusupdate.NewStatusUpdateDto;
import com.obieliakov.tasksmanager.dto.statusupdate.StatusUpdateDto;
import com.obieliakov.tasksmanager.dto.task.*;
import com.obieliakov.tasksmanager.mapper.AssigmentMapper;
import com.obieliakov.tasksmanager.mapper.StatusUpdateMapper;
import com.obieliakov.tasksmanager.mapper.TaskMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.StatusUpdate;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.repository.AssignmentRepository;
import com.obieliakov.tasksmanager.repository.StatusUpdateRepository;
import com.obieliakov.tasksmanager.repository.TaskRepository;
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
    private final AssigmentMapper assigmentMapper;

    private final TaskRepository taskRepository;
    private final StatusUpdateRepository statusUpdateRepository;
    private final AssignmentRepository assignmentRepository;

    private final IdentityService identityService;
    private final GroupMembershipService groupMembershipService;
    private final AppUserService appUserService;
    private final GroupService groupService;

    public TaskServiceImpl(Validator validator, TaskMapper taskMapper, StatusUpdateMapper statusUpdateMapper, AssigmentMapper assigmentMapper, TaskRepository taskRepository, StatusUpdateRepository statusUpdateRepository, AssignmentRepository assignmentRepository, IdentityService identityService, GroupMembershipService groupMembershipService, AppUserService appUserService, GroupService groupService) {
        this.validator = validator;
        this.taskMapper = taskMapper;
        this.statusUpdateMapper = statusUpdateMapper;
        this.assigmentMapper = assigmentMapper;
        this.taskRepository = taskRepository;
        this.statusUpdateRepository = statusUpdateRepository;
        this.assignmentRepository = assignmentRepository;
        this.identityService = identityService;
        this.groupMembershipService = groupMembershipService;
        this.appUserService = appUserService;
        this.groupService = groupService;
    }

    @Override
    public Task taskModelById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return task.get();
    }

    @Override
    public TaskDto taskById(Long id) {
        Task task = taskModelById(id);
        groupMembershipService.verifyCurrentUserMembership(task.getGroup().getId());
        return taskMapper.taskToTaskDto(task);
    }

    @Override
    public TaskDto createTask(NewTaskDto newTaskDto) {
        newTaskDto.trim();

        Set<ConstraintViolation<NewTaskDto>> violations = validator.validate(newTaskDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        UUID currentAppUserId = identityService.currentUserID();
        Long groupId = newTaskDto.getGroupId();

        groupMembershipService.verifyMembership(currentAppUserId, groupId);

        AppUser currentAppUser = appUserService.appUserModelById(currentAppUserId);
        Group group = groupService.groupModelById(groupId);

        Task newTask = taskMapper.newTaskDtoToTask(newTaskDto);
        newTask.setAddedBy(currentAppUser);
        newTask.setGroup(group);

        Task createdTask = taskRepository.save(newTask);
        return taskMapper.taskToTaskDto(createdTask);
    }

    @Override
    public TaskDto updateTaskInfo(Long id, UpdateTaskInfoDto updateTaskInfoDto) {
        updateTaskInfoDto.trim();

        Set<ConstraintViolation<UpdateTaskInfoDto>> violations = validator.validate(updateTaskInfoDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Task existingTask = taskModelById(id);

        groupMembershipService.verifyCurrentUserMembership(existingTask.getGroup().getId());

        existingTask = taskMapper.copyUpdateTaskInfoDtoToTask(updateTaskInfoDto, existingTask);

        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.taskToTaskDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task existingTask = taskModelById(id);

        groupMembershipService.verifyCurrentUserMembership(existingTask.getGroup().getId());

        taskRepository.delete(existingTask);
    }

    @Override
    public StatusUpdateDto updateTaskStatus(Long id, NewStatusUpdateDto newStatusUpdateDto) {
        Set<ConstraintViolation<NewStatusUpdateDto>> violations = validator.validate(newStatusUpdateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Task existingTask = taskModelById(id);

        UUID currentAppUserId = identityService.currentUserID();

        groupMembershipService.verifyMembership(currentAppUserId, existingTask.getGroup().getId());

        AppUser currentAppUser = appUserService.appUserModelById(currentAppUserId);

        if(existingTask.getStatus().equals(newStatusUpdateDto.getNewTaskStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task already has this status");
        }

        StatusUpdate newStatusUpdate = new StatusUpdate();
        newStatusUpdate.setStatus(newStatusUpdateDto.getNewTaskStatus());
        newStatusUpdate.setTask(existingTask);
        newStatusUpdate.setUpdatedBy(currentAppUser);

        StatusUpdate createdStatusUpdate = statusUpdateRepository.save(newStatusUpdate);

        existingTask.setStatus(newStatusUpdateDto.getNewTaskStatus());
        taskRepository.save(existingTask);

        return statusUpdateMapper.statusUpdateToStatusUpdateDto(createdStatusUpdate);
    }

    @Override
    public TaskStatusUpdatesDto taskStatusUpdates(Long id) {
        Task task = taskModelById(id);

        groupMembershipService.verifyCurrentUserMembership(task.getGroup().getId());

        List<StatusUpdate> statusUpdateList = statusUpdateRepository.findAllByTask(task);

        List<StatusUpdateDto> statusUpdateDtoList = statusUpdateMapper.statusUpdateListToStatusUpdateDtoList(statusUpdateList);

        TaskStatusUpdatesDto taskStatusUpdatesDto = taskMapper.taskToTaskStatusUpdatesDto(task);
        taskStatusUpdatesDto.setStatusUpdates(statusUpdateDtoList);
        return taskStatusUpdatesDto;
    }

    @Override
    public TaskAssignmentsDto taskAssignments(Long id) {
        Task task = taskModelById(id);
        groupMembershipService.verifyCurrentUserMembership(task.getGroup().getId());
        return taskMapper.taskToTaskAssignmentsDto(task);
    }

    @Override
    public List<TaskShortInfoDto> allTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskMapper.taskListToTaskShortInfoDtoList(taskList);
    }
}

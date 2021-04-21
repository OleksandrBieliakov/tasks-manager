package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.assignment.AssignmentDto;
import com.obieliakov.tasksmanager.dto.assignment.NewAssignmentDto;
import com.obieliakov.tasksmanager.mapper.AssigmentMapper;
import com.obieliakov.tasksmanager.model.Assignment;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.repository.AssignmentRepository;
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
public class AssignmentServiceImpl implements AssignmentService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final Validator validator;

    private final AssigmentMapper assigmentMapper;

    private final AssignmentRepository assignmentRepository;

    private final IdentityService identityService;
    private final GroupMembershipService groupMembershipService;
    private final AppUserService appUserService;
    private final TaskService taskService;

    public AssignmentServiceImpl(Validator validator, AssigmentMapper assigmentMapper, AssignmentRepository assignmentRepository, IdentityService identityService, GroupMembershipService groupMembershipService, AppUserService appUserService, TaskService taskService) {
        this.validator = validator;
        this.assigmentMapper = assigmentMapper;
        this.assignmentRepository = assignmentRepository;
        this.identityService = identityService;
        this.groupMembershipService = groupMembershipService;
        this.appUserService = appUserService;
        this.taskService = taskService;
    }

    @Override
    public Assignment assignmentModelById(Long id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if (assignment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        return assignment.get();
    }

    @Override
    public AssignmentDto assignmentById(Long id) {
        Assignment assignment = assignmentModelById(id);
        groupMembershipService.verifyCurrentUserMembership(assignment.getTask().getGroup().getId());
        return assigmentMapper.assignmentToAssignmentDto(assignment);
    }

    @Override
    public AssignmentDto createAssignment(NewAssignmentDto newAssignmentDto) {
        Set<ConstraintViolation<NewAssignmentDto>> violations = validator.validate(newAssignmentDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        UUID currentAppUserId = identityService.currentUserID();
        UUID toAppUserId = newAssignmentDto.getToAppUserId();
        Long taskId = newAssignmentDto.getTaskId();
        Task task = taskService.taskModelById(taskId);

        groupMembershipService.verifyMembership(currentAppUserId, task.getGroup().getId());
        groupMembershipService.verifyMembership(toAppUserId, task.getGroup().getId());

        Optional<Assignment> optionalAssignment = assignmentRepository.queryByTaskIdAndAssignedToAppUserId(taskId, toAppUserId);
        if(optionalAssignment.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task is already assigned to user");
        }

        Assignment newAssignment = new Assignment();
        newAssignment.setTask(task);
        newAssignment.setAssignedBy(appUserService.appUserModelById(currentAppUserId));
        newAssignment.setAssignedTo(appUserService.appUserModelById(toAppUserId));

        Assignment createdAssignment = assignmentRepository.save(newAssignment);
        return assigmentMapper.assignmentToAssignmentDto(createdAssignment);
    }

    @Override
    public void deleteAssignment(Long id) {
        Assignment assignment = assignmentModelById(id);
        groupMembershipService.verifyCurrentUserMembership(assignment.getTask().getGroup().getId());
        assignmentRepository.delete(assignment);
    }
}

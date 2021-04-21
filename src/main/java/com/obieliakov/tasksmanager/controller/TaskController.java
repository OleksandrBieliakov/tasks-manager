package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.assignment.AssignmentDto;
import com.obieliakov.tasksmanager.dto.assignment.NewAssignmentDto;
import com.obieliakov.tasksmanager.dto.statusupdate.NewStatusUpdateDto;
import com.obieliakov.tasksmanager.dto.statusupdate.StatusUpdateDto;
import com.obieliakov.tasksmanager.dto.task.*;
import com.obieliakov.tasksmanager.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/tasks")
@SecurityRequirement(name = "identity")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public NewTaskCreatedDto createTask(@RequestBody NewTaskDto newTaskDto) {
        return taskService.createTask(newTaskDto);
    }

    @PatchMapping("{id}")
    public TaskUpdatedDto updateTaskInfo(@PathVariable Long id, @RequestBody UpdateTaskInfoDto updateTaskInfoDto) {
        return taskService.updateTaskInfo(id, updateTaskInfoDto);
    }

    @PatchMapping("{id}/status-update")
    public StatusUpdateDto updateTaskStatus(@PathVariable Long id, @RequestBody NewStatusUpdateDto newStatusUpdateDto) {
        return taskService.updateTaskStatus(id, newStatusUpdateDto);
    }

    @GetMapping("{id}/status-update")
    public TaskStatusUpdatesDto updateTaskStatus(@PathVariable Long id) {
        return taskService.taskStatusUpdates(id);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("{id}/assignments")
    public TaskAssignmentsDto taskAssignments(@PathVariable Long id) {
        return taskService.taskAssignments(id);
    }

    @PostMapping("{id}/assignments")
    public AssignmentDto createAssignment(@PathVariable Long id, @RequestBody NewAssignmentDto newAssignmentDto) {
        return taskService.createAssignment(id, newAssignmentDto);
    }
}

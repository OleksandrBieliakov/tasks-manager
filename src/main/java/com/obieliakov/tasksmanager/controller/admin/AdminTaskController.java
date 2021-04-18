package com.obieliakov.tasksmanager.controller.admin;

import com.obieliakov.tasksmanager.dto.task.TaskShortInfoDto;
import com.obieliakov.tasksmanager.dto.task.TaskStatusUpdatesDto;
import com.obieliakov.tasksmanager.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/admin/tasks")
@SecurityRequirement(name = "identity")
public class AdminTaskController {

    private final TaskService taskService;

    public AdminTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}/status-update")
    public TaskStatusUpdatesDto taskStatusUpdates(@PathVariable Long id) {
        return taskService.taskStatusUpdates(id);
    }

    @GetMapping
    public List<TaskShortInfoDto> allTasks() {
        return taskService.allTasks();
    }
}
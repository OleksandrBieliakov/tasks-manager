package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.task.TaskShortInfoDto;
import com.obieliakov.tasksmanager.service.AppUserService;
import com.obieliakov.tasksmanager.service.GroupService;
import com.obieliakov.tasksmanager.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
@SecurityRequirement(name = "identity")
public class AdminController {

    private final AppUserService appUserService;
    private final GroupService groupService;
    private final TaskService taskService;

    public AdminController(AppUserService appUserService, GroupService groupService, TaskService taskService) {
        this.appUserService = appUserService;
        this.groupService = groupService;
        this.taskService = taskService;
    }

    @GetMapping("/users")
    public List<AppUserDto> allAppUsers() {
        return appUserService.allAppUsers();
    }

    @GetMapping("/groups")
    public List<GroupInfoDto> allGroups() {
        return groupService.allGroups();
    }

    @GetMapping
    public List<TaskShortInfoDto> allTasks() {
        return taskService.allTasks();
    }
}

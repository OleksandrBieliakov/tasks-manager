package com.obieliakov.tasksmanager.controller.admin;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersDto;
import com.obieliakov.tasksmanager.dto.group.GroupTasksDto;
import com.obieliakov.tasksmanager.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/groups")
@SecurityRequirement(name = "identity")
public class AdminGroupController {

    private final GroupService groupService;

    public AdminGroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public GroupInfoDto groupInfo(@PathVariable Long id) {
        return groupService.groupInfoById(id);
    }

    @GetMapping("/{id}/members")
    public GroupMembersDto groupMembers(@PathVariable Long id) {
        return groupService.groupMembersById(id, true);
    }

    @GetMapping("/{id}/tasks")
    public GroupTasksDto groupTasks(@PathVariable Long id) {
        return groupService.groupTasksById(id, true);
    }

    @GetMapping
    public List<GroupInfoDto> allGroups() {
        return groupService.allGroups();
    }
}

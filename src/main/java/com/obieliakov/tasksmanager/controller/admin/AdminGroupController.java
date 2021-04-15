package com.obieliakov.tasksmanager.controller.admin;

import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public GroupInfoDto createGroup(@RequestBody NewOrUpdateGroupDto newOrUpdateGroupDto) {
        return groupService.createGroup(newOrUpdateGroupDto);
    }

    @PatchMapping("/{id}")
    public GroupInfoDto updateGroupInfo(@PathVariable Long id, @RequestBody NewOrUpdateGroupDto newOrUpdateGroupDto) {
        return groupService.updateGroupInfo(id, newOrUpdateGroupDto);
    }

    @GetMapping("/{id}/members")
    public GroupMembersDto groupMembers(@PathVariable Long id) {
        return groupService.groupMembersById(id, true);
    }

    // added to practice custom hql query creation with projection to AppUserShortDto
    @GetMapping("/{id}/members/short")
    public GroupMembersShortDto groupMembersShort(@PathVariable Long id) {
        return groupService.groupMembersShortById(id);
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

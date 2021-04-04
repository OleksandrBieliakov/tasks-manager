package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
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
        return groupService.groupMembersById(id);
    }

    // added to practice custom hql query creation with projection to AppUserShortDto
    @GetMapping("/{id}/members/short")
    public GroupMembersShortDto groupMembersShort(@PathVariable Long id) {
        return groupService.groupMembersShortById(id);
    }

    @GetMapping("/{id}/tasks")
    public GroupTasksDto groupTasks(@PathVariable Long id) {
        return groupService.groupTasksById(id);
    }
}

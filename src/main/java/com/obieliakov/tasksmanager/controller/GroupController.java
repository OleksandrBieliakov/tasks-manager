package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersShortDto;
import com.obieliakov.tasksmanager.dto.group.NewOrUpdateGroupDto;
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

    // members - without custom querying
    @GetMapping("/{id}/members")
    public GroupMembersDto groupMembers(@PathVariable Long id) {
        return groupService.groupMembersById(id);
    }

    // members - added to practice custom hql query creation
    @GetMapping("/{id}/members/custom")
    public GroupMembersDto groupMembersCustom(@PathVariable Long id) {
        return groupService.groupMembersCustomById(id);
    }

    // members - added to practice custom hql query creation with projection to AppUserShortDto
    @GetMapping("/{id}/members/custom/short")
    public GroupMembersShortDto groupMembersCustomShort(@PathVariable Long id) {
        return groupService.groupMembersCustomShortById(id);
    }
}

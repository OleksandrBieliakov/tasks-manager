package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersDto;
import com.obieliakov.tasksmanager.dto.group.GroupTasksDto;
import com.obieliakov.tasksmanager.dto.group.NewOrUpdateGroupDto;
import com.obieliakov.tasksmanager.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/groups")
@SecurityRequirement(name = "identity")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("{id}")
    public GroupInfoDto groupInfo(@PathVariable Long id) {
        return groupService.groupInfoById(id, false);
    }

    @PostMapping
    public GroupInfoDto createGroup(@RequestBody NewOrUpdateGroupDto newOrUpdateGroupDto) {
        return groupService.createGroup(newOrUpdateGroupDto);
    }

    @PatchMapping("{id}")
    public GroupInfoDto updateGroupInfo(@PathVariable Long id, @RequestBody NewOrUpdateGroupDto newOrUpdateGroupDto) {
        return groupService.updateGroupInfo(id, newOrUpdateGroupDto);
    }

    @GetMapping("{id}/members")
    public GroupMembersDto groupMembers(@PathVariable Long id) {
        return groupService.groupMembersById(id, false);
    }


    @GetMapping("{id}/tasks")
    public GroupTasksDto groupTasks(@PathVariable Long id) {
        return groupService.groupTasksById(id, false);
    }
}

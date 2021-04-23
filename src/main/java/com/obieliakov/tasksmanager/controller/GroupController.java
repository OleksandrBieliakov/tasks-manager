package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.dto.groupMembership.GroupMembershipDto;
import com.obieliakov.tasksmanager.dto.groupinvite.GroupInviteAcceptedDto;
import com.obieliakov.tasksmanager.dto.groupinvite.GroupInviteDto;
import com.obieliakov.tasksmanager.dto.groupinvite.NewGroupInviteDto;
import com.obieliakov.tasksmanager.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("{id}/members/{appUserId}")
    public GroupMembershipDto groupMember(@PathVariable Long id, @PathVariable UUID appUserId) {
        return groupService.groupMember(id, appUserId);
    }

    @GetMapping("{id}/tasks")
    public GroupTasksDto groupTasks(@PathVariable Long id) {
        return groupService.groupTasksById(id, false);
    }

    @PostMapping("invite")
    public GroupInviteDto createGroupInvite(@RequestBody NewGroupInviteDto newGroupInviteDto) {
        return groupService.createGroupInvite(newGroupInviteDto);
    }

    @PatchMapping("invite/{id}")
    public GroupInviteAcceptedDto acceptGroupInvite(@PathVariable Long id) {
        return groupService.acceptGroupInvite(id);
    }

    @DeleteMapping("invite/{id}")
    public void declineGroupInvite(@PathVariable Long id) {
        groupService.declineGroupInvite(id);
    }

    @PatchMapping("{id}/leave")
    public void leaveGroup(@PathVariable Long id) {
        groupService.leaveGroup(id);
    }

    @GetMapping("{id}/roles")
    public GroupRolesDto groupRoles(@PathVariable Long id) {
        return groupService.groupRoles(id);
    }

    @GetMapping("{id}/members-roles")
    public GroupMembersRolesDto groupMembersRoles(@PathVariable Long id) {
        return groupService.groupMembersRolesById(id);
    }
}

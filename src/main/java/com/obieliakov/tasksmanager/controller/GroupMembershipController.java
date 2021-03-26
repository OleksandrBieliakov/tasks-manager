package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.service.GroupMembershipService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/group-memberships")
public class GroupMembershipController {

    private final GroupMembershipService groupMembershipService;

    public GroupMembershipController(GroupMembershipService groupMembershipService) {
        this.groupMembershipService = groupMembershipService;
    }

    @RequestMapping(value = "/users-short-by-group/{groupId}", method = RequestMethod.GET)
    public List<AppUserShortDto> appUsersShortByGroupId(@PathVariable("groupId") Long groupId) {
        return groupMembershipService.listAppUsersShortByGroupID(groupId);
    }
}

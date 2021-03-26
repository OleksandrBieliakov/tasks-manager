package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    public Optional<AppUserDto> userById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    //TODO split into different mappings properly
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public AppUserDto saveUser(@RequestBody AppUserDto appUserDto) {
        return appUserService.save(appUserDto);
    }

    @GetMapping("/all")
    public List<AppUserDto> allUsers() {
        return appUserService.findAll();
    }

    @GetMapping("/members-of-group/{groupId}")
    public List<AppUserDto> membersOfGroup(@PathVariable Long groupId) {
        return appUserService.getAppUsersWithMembershipInGroupWithId(groupId);
    }

    @GetMapping("/members-of-group/{groupId}/short")
    public List<AppUserShortDto> membersOfGroupShort(@PathVariable Long groupId) {
        return appUserService.listAppUsersShortMembersOfGroupWithId(groupId);
    }
}

package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.dto.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.UpdatedAppUserInfoDto;
import com.obieliakov.tasksmanager.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    public AppUserDto userById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    @GetMapping("/{loginName}")
    public AppUserDto userByLoginName(@PathVariable String loginName) {
        return appUserService.findByLoginName(loginName);
    }

    @PostMapping
    public AppUserDto createUser(@RequestBody NewAppUserDto newAppUserDto) {
        return appUserService.createUser(newAppUserDto);
    }

    @PatchMapping
    public AppUserDto updateUserInfo(@RequestBody UpdatedAppUserInfoDto updatedAppUserInfoDto) {
        return appUserService.updateUserInfo(updatedAppUserInfoDto);
    }

    @GetMapping("/all")
    public List<AppUserDto> allUsers() {
        return appUserService.findAll();
    }

    // added to practice custom hql query creation, better use some Group DTO
    @GetMapping("/members-of-group/{groupId}")
    public List<AppUserDto> membersOfGroup(@PathVariable Long groupId) {
        return appUserService.getAppUsersWithMembershipInGroupWithId(groupId);
    }

    // added to practice custom hql query creation with projection, better use some Group DTO
    @GetMapping("/members-of-group/{groupId}/short")
    public List<AppUserShortDto> membersOfGroupShort(@PathVariable Long groupId) {
        return appUserService.listAppUsersShortMembersOfGroupWithId(groupId);
    }
}

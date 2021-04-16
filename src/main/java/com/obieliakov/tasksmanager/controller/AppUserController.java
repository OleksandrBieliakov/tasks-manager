package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.appUser.*;
import com.obieliakov.tasksmanager.service.AppUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users")
@SecurityRequirement(name = "identity")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    public AppUserDto appUserById(@PathVariable UUID id) {
        return appUserService.appUserById(id, false);
    }

    @GetMapping("login-name/{loginName}")
    public AppUserDto appUserByLoginName(@PathVariable String loginName) {
        return appUserService.appUserByLoginName(loginName, false);
    }

    @PostMapping
    public AppUserFullInfoDto createAppUser(@RequestBody NewAppUserDto newAppUserDto) {
        return appUserService.createAppUser(newAppUserDto);
    }

    @PatchMapping("/{id}/login-name")
    public AppUserFullInfoDto updateAppUserLoginName(@PathVariable UUID id, @RequestBody UpdateLoginNameDto updateLoginNameDto) {
        return appUserService.updateAppUserLoginName(id, updateLoginNameDto);
    }

    @PatchMapping("{id}/privacy")
    public AppUserFullInfoDto updateAppUserPrivacySettings(@PathVariable UUID id, @RequestBody UpdatePrivacySettingsDto updatePrivacySettingsDto) {
        return appUserService.updateAppUserPrivacySettings(id, updatePrivacySettingsDto);
    }
}

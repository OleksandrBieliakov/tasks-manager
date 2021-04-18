package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.appUser.*;
import com.obieliakov.tasksmanager.service.AppUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/users")
@SecurityRequirement(name = "identity")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("{id}")
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

    @PatchMapping("login-name")
    public AppUserFullInfoDto updateAppUserLoginName(@RequestBody UpdateLoginNameDto updateLoginNameDto) {
        return appUserService.updateAppUserLoginName(updateLoginNameDto);
    }

    @PatchMapping("privacy")
    public AppUserFullInfoDto updateAppUserPrivacySettings(@RequestBody UpdatePrivacySettingsDto updatePrivacySettingsDto) {
        return appUserService.updateAppUserPrivacySettings(updatePrivacySettingsDto);
    }
}

package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.UpdateAppUserInfoDto;
import com.obieliakov.tasksmanager.service.AppUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@SecurityRequirement(name = "identity")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    public AppUserDto appUserById(@PathVariable Long id) {
        return appUserService.appUserById(id);
    }

    @GetMapping("login-name/{loginName}")
    public AppUserDto appUserByLoginName(@PathVariable String loginName) {
        return appUserService.appUserByLoginName(loginName);
    }

    @PostMapping
    public AppUserDto createAppUser(@RequestBody NewAppUserDto newAppUserDto) {
        return appUserService.createAppUser(newAppUserDto);
    }

    @PatchMapping("/{id}")
    public AppUserDto updateAppUserInfo(@PathVariable Long id, @RequestBody UpdateAppUserInfoDto updateAppUserInfoDto) {
        return appUserService.updateAppUserInfo(id, updateAppUserInfoDto);
    }

    @GetMapping
    public List<AppUserDto> allAppUsers() {
        return appUserService.allAppUsers();
    }

    @GetMapping("/current")
    public AppUserDto currentUser() {
        return appUserService.currentUser();
    }
}

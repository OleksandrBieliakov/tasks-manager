package com.obieliakov.tasksmanager.controller.admin;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.service.AppUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/admin/users")
@SecurityRequirement(name = "identity")
public class AdminAppUserController {

    private final AppUserService appUserService;

    public AdminAppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("{id}")
    public AppUserDto appUserById(@PathVariable UUID id) {
        return appUserService.appUserById(id, true);
    }

    @GetMapping("login-name/{loginName}")
    public AppUserDto appUserByLoginName(@PathVariable String loginName) {
        return appUserService.appUserByLoginName(loginName, true);
    }

    @GetMapping
    public List<AppUserDto> allAppUsers() {
        return appUserService.allAppUsers();
    }
}

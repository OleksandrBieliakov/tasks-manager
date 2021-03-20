package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.service.AppUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostConstruct
    public void init() {
        System.out.println("AppUserController initialized with" + appUserService);
    }
    
}

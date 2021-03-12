package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/api/users")
@AllArgsConstructor
public class AppUserController {

    private IAppUserService appUserService;

    @PostConstruct
    public void init() {
        System.out.println("AppUserController initialized with" + appUserService);
    }



}

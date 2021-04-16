package com.obieliakov.tasksmanager.dto.appUser;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class AppUserFullInfoDto {

    private UUID id;
    private String loginName;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean publicEmail;
    private Boolean publicFirstLastName;
    private ZonedDateTime timeRegistered;
}

package com.obieliakov.tasksmanager.dto.appUser;

import lombok.Data;

import java.util.UUID;

@Data
public class AppUserDto {

    private UUID id;
    private String loginName;
    private String email;
    private String firstName;
    private String lastName;
}

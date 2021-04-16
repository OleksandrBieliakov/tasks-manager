package com.obieliakov.tasksmanager.dto.appUser;

import lombok.Data;

import java.util.UUID;

@Data
public class AppUserIdentityDto {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
}

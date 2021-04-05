package com.obieliakov.tasksmanager.dto.appUser;

import lombok.Data;

@Data
public class AppUserDto {

    private Long id;
    private String loginName;
    private String firstName;
    private String lastName;
}

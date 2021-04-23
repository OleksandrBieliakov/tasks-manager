package com.obieliakov.tasksmanager.dto.appUser;

import com.obieliakov.tasksmanager.dto.role.RoleShortDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AppUserRolesDto {

    UUID appUserId;
    List<RoleShortDto> roles;
}

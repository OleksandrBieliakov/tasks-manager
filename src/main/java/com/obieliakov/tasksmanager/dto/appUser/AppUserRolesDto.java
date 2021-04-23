package com.obieliakov.tasksmanager.dto.appUser;

import com.obieliakov.tasksmanager.dto.role.RoleShortDto;
import lombok.Data;

import java.util.List;

@Data
public class AppUserRolesDto {

    AppUserDto appUser;
    List<RoleShortDto> roles;
}

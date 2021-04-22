package com.obieliakov.tasksmanager.dto.role;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import lombok.Data;

@Data
public class RoleAssignmentDto {

    private AppUserDto appUser;
    private RoleDto role;
}

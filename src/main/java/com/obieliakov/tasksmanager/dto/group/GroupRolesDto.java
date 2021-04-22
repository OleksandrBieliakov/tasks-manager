package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.dto.role.RoleShortDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupRolesDto {

    private Long id;
    private String name;
    private List<RoleShortDto> roles;
}

package com.obieliakov.tasksmanager.dto.role;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import lombok.Data;

@Data
public class RoleDto {

    private Long id;
    private String title;
    private GroupInfoDto group;
}

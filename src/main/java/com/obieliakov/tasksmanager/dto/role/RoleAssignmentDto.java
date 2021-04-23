package com.obieliakov.tasksmanager.dto.role;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleAssignmentDto {

    private UUID appUserId;
    private RoleShortDto role;
}

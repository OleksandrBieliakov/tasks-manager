package com.obieliakov.tasksmanager.dto.assignment;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AssignmentShortDto {

    private Long id;
    private ZonedDateTime timeAssigned;
    private AppUserDto assignedBy;
    private AppUserDto assignedTo;
}

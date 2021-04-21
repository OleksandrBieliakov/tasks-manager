package com.obieliakov.tasksmanager.dto.assignment;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.task.TaskShortInfoDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AssignmentDto {

    private Long id;
    private ZonedDateTime timeAssigned;
    private TaskShortInfoDto task;
    private AppUserDto assignedBy;
    private AppUserDto assignedTo;
}

package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private ZonedDateTime timeAdded;
    private TaskStatus status;
    private AppUserDto addedBy;
}

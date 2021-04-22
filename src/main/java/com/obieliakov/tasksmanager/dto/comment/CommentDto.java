package com.obieliakov.tasksmanager.dto.comment;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.task.TaskShortInfoDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CommentDto {

    private Long id;
    private String message;
    private ZonedDateTime timeAdded = ZonedDateTime.now();
    private TaskShortInfoDto task;
    private AppUserDto addedBy;
}

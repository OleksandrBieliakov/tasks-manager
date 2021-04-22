package com.obieliakov.tasksmanager.dto.comment;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CommentShortDto {

    private Long id;
    private String message;
    private ZonedDateTime timeAdded = ZonedDateTime.now();
    private AppUserDto addedBy;
}

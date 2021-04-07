package com.obieliakov.tasksmanager.dto.statusupdate;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class StatusUpdateDto {

    private Long id;
    private TaskStatus status;
    private ZonedDateTime timeUpdated;
    private AppUserDto updatedBy;
}

package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.dto.statusupdate.StatusUpdateDto;
import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class TaskStatusUpdatesDto {

    private Long id;
    private String title;
    private TaskStatus status;
    private ZonedDateTime timeAdded;
    private List<StatusUpdateDto> statusUpdates;
}

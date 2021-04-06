package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class NewTaskCreatedDto {

    private Long id;
    private String title;
    private TaskStatus status;
    private ZonedDateTime timeAdded;
}

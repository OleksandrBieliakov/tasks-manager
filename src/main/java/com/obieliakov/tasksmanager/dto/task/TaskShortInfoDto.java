package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

@Data
public class TaskShortInfoDto {

    private Long id;
    private String title;
    private TaskStatus status;
}

package com.obieliakov.tasksmanager.dto.task;

import lombok.Data;

@Data
public class TaskUpdatedDto {

    private Long id;
    private String title;
    private String description;
}

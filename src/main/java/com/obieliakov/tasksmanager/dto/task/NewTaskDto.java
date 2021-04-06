package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.model.Task;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewTaskDto {

    @NotNull
    @Size(min = Task.TITLE_MIN_LENGTH, max = Task.TITLE_MAX_LENGTH)
    private String title;

    @Size(min = Task.DESCRIPTION_MIN_LENGTH, max = Task.DESCRIPTION_MAX_LENGTH)
    private String description;

    @NotNull
    private Long groupId;

    @NotNull
    private Long addedByAppUserId;

    public void trim() {
        if (title != null) {
            title = title.trim();
        }
        if (description != null) {
            description = description.trim();
        }
    }
}

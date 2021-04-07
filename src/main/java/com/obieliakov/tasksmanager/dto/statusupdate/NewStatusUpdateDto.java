package com.obieliakov.tasksmanager.dto.statusupdate;

import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewStatusUpdateDto {

    @NotNull
    private TaskStatus newTaskStatus;

    @NotNull
    private Long updatedByAppUserId;
}

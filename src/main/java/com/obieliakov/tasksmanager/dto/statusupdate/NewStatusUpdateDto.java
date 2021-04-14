package com.obieliakov.tasksmanager.dto.statusupdate;

import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class NewStatusUpdateDto {

    @NotNull
    private TaskStatus newTaskStatus;

    @NotNull
    private UUID updatedByAppUserId;
}

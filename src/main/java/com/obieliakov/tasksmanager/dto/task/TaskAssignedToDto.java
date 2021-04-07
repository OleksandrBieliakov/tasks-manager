package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.util.List;

@Data
public class TaskAssignedToDto {

    private Long id;
    private String title;
    private TaskStatus status;
    private List<AppUserDto> assignedTo;
}

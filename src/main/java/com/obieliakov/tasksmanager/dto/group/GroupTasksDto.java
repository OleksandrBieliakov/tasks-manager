package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.dto.task.TaskAssignedToDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupTasksDto {

    private Long id;
    private String name;
    private List<TaskAssignedToDto> tasks;
}

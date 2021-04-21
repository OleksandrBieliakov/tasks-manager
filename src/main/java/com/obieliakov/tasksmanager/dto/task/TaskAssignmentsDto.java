package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.dto.assignment.AssignmentShortDto;
import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.util.List;

@Data
public class TaskAssignmentsDto {

    private Long id;
    private String title;
    private TaskStatus status;

    private List<AssignmentShortDto> assignments;
}

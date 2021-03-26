package com.obieliakov.tasksmanager.dto;

import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskShortDto {

    //TODO make custom repository query to get a list of TasksShort by groupId
    private Long id;
    private String title;
    private TaskStatus status;
    private Set<AppUserShortDto> assignedTo;
}

package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
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

    public TaskShortDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
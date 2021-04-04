package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.dto.task.TaskInfoDto;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GroupTasksDto {

    private Long id;
    private String name;
    private List<TaskInfoDto> tasks;
}

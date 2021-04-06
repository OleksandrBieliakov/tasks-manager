package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.task.NewTaskCreatedDto;
import com.obieliakov.tasksmanager.dto.task.NewTaskDto;
import com.obieliakov.tasksmanager.dto.task.TaskInfoDto;
import com.obieliakov.tasksmanager.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskInfoDto taskToTaskInfoDto(Task task);

    List<TaskInfoDto> taskListToTaskInfoDtoList(List<Task> tasks);

    Task newTaskDtoToTask (NewTaskDto newTaskDto);

    NewTaskCreatedDto taskToNewTaskCreatedDto(Task task);
}

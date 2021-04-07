package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.task.*;
import com.obieliakov.tasksmanager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskAssignedToDto taskToTaskAssignedToDto(Task task);

    List<TaskAssignedToDto> taskListToTaskAssignedToDtoList(List<Task> tasks);

    Task newTaskDtoToTask (NewTaskDto newTaskDto);

    NewTaskCreatedDto taskToNewTaskCreatedDto(Task task);

    TaskShortInfoDto taskToTaskShortInfoDto(Task task);

    List<TaskShortInfoDto> taskListToTaskShortInfoDtoList(List<Task> tasks);

    TaskUpdatedDto taskToTaskUpdatedDto(Task task);

    Task copyUpdateTaskInfoDtoToTask(UpdateTaskInfoDto updateTaskInfoDto, @MappingTarget Task task);

    @Mapping(target = "statusUpdates", ignore = true)
    TaskStatusUpdatesDto taskToTaskStatusUpdatesDto(Task task);
}

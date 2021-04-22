package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.task.*;
import com.obieliakov.tasksmanager.model.Task;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AppUserWithPrivacyMapper.class, AssigmentMapper.class, CommentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);

    TaskAssignedToDto taskToTaskAssignedToDto(Task task);

    List<TaskAssignedToDto> taskListToTaskAssignedToDtoList(List<Task> tasks);

    Task newTaskDtoToTask (NewTaskDto newTaskDto);

    TaskShortInfoDto taskToTaskShortInfoDto(Task task);

    List<TaskShortInfoDto> taskListToTaskShortInfoDtoList(List<Task> tasks);

    Task copyUpdateTaskInfoDtoToTask(UpdateTaskInfoDto updateTaskInfoDto, @MappingTarget Task task);

    @Mapping(target = "statusUpdates", ignore = true)
    TaskStatusUpdatesDto taskToTaskStatusUpdatesDto(Task task);

    TaskAssignmentsDto taskToTaskAssignmentsDto(Task task);

    TaskCommentsDto taskToTaskCommentsDto(Task task);
}

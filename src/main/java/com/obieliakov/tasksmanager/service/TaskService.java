package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.statusupdate.NewStatusUpdateDto;
import com.obieliakov.tasksmanager.dto.statusupdate.StatusUpdateDto;
import com.obieliakov.tasksmanager.dto.task.*;

import java.util.List;

public interface TaskService {

    NewTaskCreatedDto createTask(NewTaskDto newTaskDto);

    TaskUpdatedDto updateTaskInfo(Long id, UpdateTaskInfoDto updateTaskInfoDto);

    void deleteTask(Long id);

    StatusUpdateDto updateTaskStatus(Long id, NewStatusUpdateDto newStatusUpdateDto);

    TaskStatusUpdatesDto taskStatusUpdates(Long id);

    List<TaskShortInfoDto> allTasks();
}

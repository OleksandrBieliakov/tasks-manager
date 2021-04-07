package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.task.*;

import java.util.List;

public interface TaskService {

    NewTaskCreatedDto createTask(NewTaskDto newTaskDto);

    TaskUpdatedDto updateTaskInfo(Long id, UpdateTaskInfoDto updateTaskInfoDto);

    void deleteTask(Long id);

    List<TaskShortInfoDto> allTasks();
}

package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.task.NewTaskCreatedDto;
import com.obieliakov.tasksmanager.dto.task.NewTaskDto;
import com.obieliakov.tasksmanager.dto.task.TaskDto;

import java.util.List;

public interface TaskService {

    NewTaskCreatedDto createTask(NewTaskDto newTaskDto);

    void deleteTask(Long id);

    List<TaskDto> allTasks();
}

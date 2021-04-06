package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.task.NewTaskCreatedDto;
import com.obieliakov.tasksmanager.dto.task.NewTaskDto;

public interface TaskService {

    NewTaskCreatedDto createTask(NewTaskDto newTaskDto);
}

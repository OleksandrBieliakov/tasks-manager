package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.task.NewTaskCreatedDto;
import com.obieliakov.tasksmanager.dto.task.NewTaskDto;
import com.obieliakov.tasksmanager.dto.task.TaskDto;
import com.obieliakov.tasksmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public NewTaskCreatedDto createTask(@RequestBody NewTaskDto newTaskDto) {
        return taskService.createTask(newTaskDto);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping
    public List<TaskDto> allTasks() {
        return taskService.allTasks();
    }
}

package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.task.*;
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

    @PatchMapping("{id}")
    public TaskUpdatedDto updateTaskInfo(@PathVariable Long id, @RequestBody UpdateTaskInfoDto updateTaskInfoDto) {
        return taskService.updateTaskInfo(id, updateTaskInfoDto);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping
    public List<TaskShortInfoDto> allTasks() {
        return taskService.allTasks();
    }
}

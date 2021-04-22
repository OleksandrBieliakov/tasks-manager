package com.obieliakov.tasksmanager.dto.task;

import com.obieliakov.tasksmanager.dto.comment.CommentShortDto;
import com.obieliakov.tasksmanager.model.TaskStatus;
import lombok.Data;

import java.util.List;

@Data
public class TaskCommentsDto {

    private Long id;
    private String title;
    private TaskStatus status;
    private List<CommentShortDto> comments;
}

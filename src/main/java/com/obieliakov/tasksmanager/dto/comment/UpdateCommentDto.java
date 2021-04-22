package com.obieliakov.tasksmanager.dto.comment;

import com.obieliakov.tasksmanager.model.Comment;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateCommentDto {

    @NotNull
    @Size(min = Comment.MESSAGE_MIN_LENGTH, max = Comment.MESSAGE_MAX_LENGTH)
    private String message;

    public void trim() {
        if (message != null) {
            message = message.trim();
        }
    }
}

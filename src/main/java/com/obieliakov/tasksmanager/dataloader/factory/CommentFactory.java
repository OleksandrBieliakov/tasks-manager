package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Comment;
import com.obieliakov.tasksmanager.model.Task;
import org.springframework.stereotype.Component;

@Component
public class CommentFactory implements Factory<Comment> {

    public static final String MESSAGE = "message";

    private int next_serial_number = 1;

    @Override
    public Comment generate() {
        int serial_number = next_serial_number;
        Comment comment = new Comment();
        comment.setMessage(format(MESSAGE, serial_number));
        next_serial_number++;
        return comment;
    }

    public Comment generateAndInit(Task task, AppUser addedBy) {
        Comment comment = generate();
        comment.setTask(task);
        comment.setAddedBy(addedBy);
        return comment;
    }
}

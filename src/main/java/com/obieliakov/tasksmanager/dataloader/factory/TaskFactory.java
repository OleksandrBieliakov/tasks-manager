package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.model.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskFactory implements Factory<Task> {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    private int next_serial_number = 1;

    @Override
    public Task generate() {
        int serial_number = next_serial_number;
        Task task = new Task();
        task.setTitle(format(TITLE, serial_number));
        task.setDescription(format(DESCRIPTION, serial_number));
        next_serial_number++;
        return task;
    }

    public Task generateAndInit(Group group, AppUser addedBy, TaskStatus status) {
        Task task = generate();
        task.setGroup(group);
        task.setAddedBy(addedBy);
        task.setStatus(status);
        return task;
    }
}

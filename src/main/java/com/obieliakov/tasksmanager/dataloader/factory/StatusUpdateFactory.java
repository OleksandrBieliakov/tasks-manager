package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.StatusUpdate;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.model.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class StatusUpdateFactory implements Factory<StatusUpdate> {

    @Override
    public StatusUpdate generate() {
        return new StatusUpdate();
    }

    public StatusUpdate generateAndInit(Task task, AppUser updatedBy, TaskStatus status) {
        StatusUpdate statusUpdate = generate();
        statusUpdate.setTask(task);
        statusUpdate.setUpdatedBy(updatedBy);
        statusUpdate.setStatus(status);
        return statusUpdate;
    }
}

package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Assignment;
import com.obieliakov.tasksmanager.model.Task;
import org.springframework.stereotype.Component;

@Component
public class AssignmentFactory implements Factory<Assignment> {

    @Override
    public Assignment generate() {
        return new Assignment();
    }

    public Assignment generateAndInit(Task task, AppUser assignedBy, AppUser assignedTo) {
        Assignment assignment = generate();
        assignment.setTask(task);
        assignment.setAssignedBy(assignedBy);
        assignment.setAssignedTo(assignedTo);
        return  assignment;
    }
}

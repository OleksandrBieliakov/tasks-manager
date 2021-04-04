package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory implements Factory<Role>{

    public static final String TITLE = "title";

    private int next_serial_number = 1;

    @Override
    public Role generate() {
        int serial_number = next_serial_number;
        Role role = new Role();
        role.setTitle(format(TITLE, serial_number));
        next_serial_number++;
        return role;
    }

    public Role generateAndInit(Group group) {
        Role role = generate();
        role.setGroup(group);
        return role;
    }
}

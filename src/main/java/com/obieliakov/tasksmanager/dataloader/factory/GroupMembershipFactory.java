package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import com.obieliakov.tasksmanager.model.Role;
import org.springframework.stereotype.Component;

@Component
public class GroupMembershipFactory implements Factory<GroupMembership> {

    @Override
    public GroupMembership generate() {
        return new GroupMembership();
    }

    public GroupMembership generateAndInit(Group group, AppUser appUser) {
        GroupMembership groupMembership = generate();
        groupMembership.setGroup(group);
        groupMembership.setAppUser(appUser);
        return groupMembership;
    }

    public GroupMembership generateAndInit(Group group, AppUser appUser, Role role) {
        GroupMembership groupMembership = generate();
        groupMembership.setGroup(group);
        groupMembership.setAppUser(appUser);
        groupMembership.setRole(role);
        return groupMembership;
    }
}

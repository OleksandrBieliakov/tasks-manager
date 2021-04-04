package com.obieliakov.tasksmanager.dataloader.factory;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupInvite;
import org.springframework.stereotype.Component;

@Component
public class GroupInviteFactory implements Factory<GroupInvite> {

    @Override
    public GroupInvite generate() {
        return new GroupInvite();
    }

    public GroupInvite generateAndInit(Group group, AppUser byAppUser, AppUser toAppUser) {
        GroupInvite groupInvite = generate();
        groupInvite.setGroup(group);
        groupInvite.setByAppUser(byAppUser);
        groupInvite.setToAppUser(toAppUser);
        return groupInvite;
    }
}

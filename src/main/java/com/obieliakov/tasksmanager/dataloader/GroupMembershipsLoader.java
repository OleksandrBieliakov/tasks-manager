package com.obieliakov.tasksmanager.dataloader;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import com.obieliakov.tasksmanager.repository.GroupMembershipRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GroupMembershipsLoader implements Loader<GroupMembership> {

    private final GroupMembershipRepository repository;

    public GroupMembershipsLoader(GroupMembershipRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GroupMembership> generate(String tag, int number, int from) {
        List<GroupMembership> groupMemberships = new ArrayList<>();
        for (int i = from; i < from + number; i++) {
            GroupMembership groupMembership = new GroupMembership();
            groupMemberships.add(groupMembership);
        }
        return groupMemberships;
    }

    @Override
    public void load(List<GroupMembership> entities) {
        repository.saveAll(entities);
    }

    private void assignAppUsersToGroupsCycle(
            List<AppUser> appUsers, List<Group> groups, List<GroupMembership> groupMemberships,
            AtomicInteger membership_ind, AtomicInteger user_ind, AtomicInteger group_ind,
            int usersNumber, int groupsNumber) {
        int till_user_ind = user_ind.get() + usersNumber;
        while (user_ind.get() < till_user_ind) {
            for (int i = 0; i < groupsNumber; i++) {
                if (group_ind.get() == groups.size()) {
                    group_ind.set(0);
                }
                GroupMembership membership = groupMemberships.get(membership_ind.get());
                membership.setGroup(groups.get(group_ind.get()));
                membership.setUser(appUsers.get(user_ind.get()));
                membership_ind.incrementAndGet();
                group_ind.incrementAndGet();
            }
            user_ind.incrementAndGet();
        }
    }

    public void assignAppUsersToGroups(List<AppUser> appUsers, List<Group> groups, List<GroupMembership> groupMemberships,
                                       int[] memberships_numbers) {
        AtomicInteger membership_ind = new AtomicInteger(0);
        AtomicInteger user_ind = new AtomicInteger(0);
        AtomicInteger group_ind = new AtomicInteger(0);

        for (int index = 0; index < memberships_numbers.length; index++) {
            assignAppUsersToGroupsCycle(
                    appUsers, groups, groupMemberships, membership_ind, user_ind, group_ind, memberships_numbers[index], index);
        }
    }
}

package com.obieliakov.tasksmanager.dataloader;

import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupMembership;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoaderTest implements CommandLineRunner {

    private static final String TAG = "lr";
    private static final int ENUMERATE_FROM = 1;

    private static final int NUMBER_OF_GROUPS = 2;
    private static final int NUMBER_OF_USERS = 6;

    private static final int[] MEMBERSHIPS_NUMBERS = {1, 3, 2}; // index - number of groups; value - number of users
    private static final int NUMBER_OF_MEMBERSHIPS = IntStream.
            range(0, MEMBERSHIPS_NUMBERS.length).
            reduce(0, (sum, index) -> sum += index * MEMBERSHIPS_NUMBERS[index]);

    private final AppUsersLoader appUsersLoader;
    private final GroupsLoader groupsLoader;
    private final GroupMembershipsLoader groupMembershipsLoader;

    public DatabaseLoaderTest(AppUsersLoader appUsersLoader, GroupsLoader groupsLoader, GroupMembershipsLoader groupMembershipsLoader) {
        this.appUsersLoader = appUsersLoader;
        this.groupsLoader = groupsLoader;
        this.groupMembershipsLoader = groupMembershipsLoader;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<AppUser> appUsers = appUsersLoader.generateAndInit(TAG, NUMBER_OF_USERS, ENUMERATE_FROM);
        appUsersLoader.load(appUsers);

        List<Group> groups = groupsLoader.generate(TAG, NUMBER_OF_GROUPS, ENUMERATE_FROM);
        groupsLoader.load(groups);

        List<GroupMembership> groupMemberships = groupMembershipsLoader.generate(TAG, NUMBER_OF_MEMBERSHIPS, ENUMERATE_FROM);
        groupMembershipsLoader.assignAppUsersToGroups(appUsers, groups, groupMemberships, MEMBERSHIPS_NUMBERS);
        groupMembershipsLoader.load(groupMemberships);
    }
}
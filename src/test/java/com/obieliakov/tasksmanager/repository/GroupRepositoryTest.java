package com.obieliakov.tasksmanager.repository;

import com.obieliakov.tasksmanager.dataloader.GroupsLoader;
import com.obieliakov.tasksmanager.model.Group;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupRepositoryTest {

    private static final String TAG = "GroupRepositoryTest";
    private static final int ENUMERATE_FROM = 1;
    private static final int NUMBER_OF_GROUPS = 1;

    @Autowired
    private GroupsLoader groupsLoader;

    @Autowired
    private GroupRepository groupRepository;

    private List<Group> loadedGroups;

    @BeforeEach
    void setUp() {
        loadedGroups = groupsLoader.generate(TAG, NUMBER_OF_GROUPS, ENUMERATE_FROM);
        groupsLoader.load(loadedGroups);
    }

    @AfterEach
    void tearDown() {
        groupRepository.deleteAll(loadedGroups);
    }

    @Test
    void findByName() {
        String name = groupsLoader.format(TAG, GroupsLoader.GROUP_NAME, ENUMERATE_FROM);
        Group group = groupRepository.findByName(name);
        assertNotNull(group);
        assertEquals(name, group.getName());

        Group notExisting = groupRepository.findByName("not existing");
        assertNull(notExisting);
    }
}
package com.obieliakov.tasksmanager.dataloader;

import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.repository.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsLoader implements Loader<Group> {

    public static final String GROUP_NAME = "group_name";

    private final GroupRepository repository;

    public GroupsLoader(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Group> generate(String tag, int number, int from) {
        List<Group> groups = new ArrayList<>();
        for (int i = from; i < from + number; i++) {
            Group group = new Group();
            group.setName(format(tag, GROUP_NAME, i));
            groups.add(group);
        }
        return groups;
    }

    @Override
    public void load(List<Group> entities) {
        repository.saveAll(entities);
    }
}

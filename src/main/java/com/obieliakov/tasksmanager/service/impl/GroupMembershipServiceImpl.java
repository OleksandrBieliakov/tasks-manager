package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.repository.GroupMembershipRepository;
import com.obieliakov.tasksmanager.service.GroupMembershipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMembershipServiceImpl implements GroupMembershipService {

    private final GroupMembershipRepository groupMembershipRepository;

    public GroupMembershipServiceImpl(GroupMembershipRepository groupMembershipRepository) {
        this.groupMembershipRepository = groupMembershipRepository;
    }

    @Override
    public List<AppUserShortDto> listAppUsersShortByGroupID(Long groupId) {
        return groupMembershipRepository.queryAppUsersShortByGroupId(groupId);
    }
}

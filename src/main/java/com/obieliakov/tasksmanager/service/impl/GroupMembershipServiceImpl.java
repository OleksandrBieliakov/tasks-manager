package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.repository.GroupMembershipRepository;
import com.obieliakov.tasksmanager.service.GroupMembershipService;
import com.obieliakov.tasksmanager.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
public class GroupMembershipServiceImpl implements GroupMembershipService {

    private final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    private final GroupMembershipRepository groupMembershipRepository;

    private final IdentityService identityService;

    public GroupMembershipServiceImpl(GroupMembershipRepository groupMembershipRepository, IdentityService identityService) {
        this.groupMembershipRepository = groupMembershipRepository;
        this.identityService = identityService;
    }

    @Override
    public boolean isAppUserMemberOfGroup(UUID appUserId, Long groupID) {
        return groupMembershipRepository.queryByGroupIdAndAppUserIdAndActiveTrue(groupID, appUserId).isPresent();
    }

    @Override
    public void verifyMembership(UUID appUserId, Long groupId) {
        if(!isAppUserMemberOfGroup(appUserId, groupId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not a member of a group");
        }
    }

    @Override
    public void verifyCurrentUserMembership(Long groupId) {
        UUID appUserId = identityService.currentUserID();
        verifyMembership(appUserId, groupId);
    }
}

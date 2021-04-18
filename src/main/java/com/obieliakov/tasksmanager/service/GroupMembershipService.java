package com.obieliakov.tasksmanager.service;

import java.util.UUID;

public interface GroupMembershipService {

    boolean isAppUserMemberOfGroup(UUID appUserId, Long groupID);

    void verifyMembership(UUID appUserId, Long groupId);

    void verifyCurrentUserMembership(Long groupId);
}

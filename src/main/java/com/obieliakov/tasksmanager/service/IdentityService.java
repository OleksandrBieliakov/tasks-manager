package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.AppUserIdentityDto;

import java.util.UUID;

public interface IdentityService {

    AppUserIdentityDto currentUser();

    UUID currentUserID();

    boolean unauthorized(UUID checkedUserId);

    void verifyAuthorization(UUID checkedUserId);

    boolean unauthorized(UUID currentUserId, UUID checkedUserId);

    void verifyAuthorization(UUID currentUserId, UUID checkedUserId);
}

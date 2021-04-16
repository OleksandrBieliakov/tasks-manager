package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.AppUserIdentityDto;

import java.util.UUID;

public interface IdentityService {

    AppUserIdentityDto currentUser();

    UUID currentUserID();

    boolean unauthorized(UUID checkedUserId);

    boolean unauthorized(AppUserIdentityDto currentUser, UUID checkedUserId);
}

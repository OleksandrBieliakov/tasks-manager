package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;

public interface IdentityService {

    AppUserDto currentUser();
}

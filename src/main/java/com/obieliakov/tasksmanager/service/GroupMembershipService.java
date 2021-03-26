package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.AppUserShortDto;

import java.util.List;

public interface GroupMembershipService {

    List<AppUserShortDto> listAppUsersShortByGroupID(Long groupId);
}

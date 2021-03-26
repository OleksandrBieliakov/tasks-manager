package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.dto.AppUserShortDto;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    Optional<AppUserDto> findById(Long id);

    AppUserDto save(AppUserDto appUserDto);

    List<AppUserDto> findAll();

    List<AppUserDto> getAppUsersWithMembershipInGroupWithId(Long id);

    List<AppUserShortDto> listAppUsersShortMembersOfGroupWithId(Long groupId);
}

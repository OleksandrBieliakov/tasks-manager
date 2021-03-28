package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.dto.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.UpdatedAppUserInfoDto;

import java.util.List;

public interface AppUserService {

    AppUserDto findById(Long id);

    AppUserDto findByLoginName(String loginName);

    AppUserDto createUser(NewAppUserDto newAppUserDto);

    AppUserDto updateUserInfo(UpdatedAppUserInfoDto updatedAppUserInfoDto);

    List<AppUserDto> findAll();

    // added to practice custom hql query creation, better use some Group DTO
    List<AppUserDto> getAppUsersWithMembershipInGroupWithId(Long id);

    // added to practice custom hql query creation with projection, better use some Group DTO
    List<AppUserShortDto> listAppUsersShortMembersOfGroupWithId(Long groupId);
}

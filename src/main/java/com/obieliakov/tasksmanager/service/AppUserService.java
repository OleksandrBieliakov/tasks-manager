package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.UpdateAppUserInfoDto;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    AppUserDto appUserById(UUID id);

    AppUserDto appUserByLoginName(String loginName);

    AppUserDto createAppUser(NewAppUserDto newAppUserDto);

    AppUserDto updateAppUserInfo(UUID id, UpdateAppUserInfoDto updateAppUserInfoDto);

    List<AppUserDto> allAppUsers();

    AppUserDto currentUser();
}

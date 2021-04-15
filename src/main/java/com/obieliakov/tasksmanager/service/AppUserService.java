package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.UpdateLoginNameDto;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    AppUserDto appUserById(UUID id, boolean isAdmin);

    AppUserDto appUserByLoginName(String loginName, boolean isAdmin);

    AppUserDto createAppUser(NewAppUserDto newAppUserDto);

    AppUserDto updateAppUserLoginName(UUID id, UpdateLoginNameDto updateLoginNameDto);

    List<AppUserDto> allAppUsers();
}

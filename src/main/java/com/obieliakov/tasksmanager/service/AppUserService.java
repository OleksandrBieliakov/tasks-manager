package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.UpdateAppUserInfoDto;

import java.util.List;

public interface AppUserService {

    AppUserDto appUserById(Long id);

    AppUserDto appUserByLoginName(String loginName);

    AppUserDto createAppUser(NewAppUserDto newAppUserDto);

    AppUserDto updateAppUserInfo(Long userid, UpdateAppUserInfoDto updateAppUserInfoDto);

    List<AppUserDto> allAppUsers();
}

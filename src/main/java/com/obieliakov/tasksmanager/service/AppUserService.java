package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.*;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    AppUserDto appUserById(UUID id, boolean isAdmin);

    AppUserDto appUserByLoginName(String loginName, boolean isAdmin);

    AppUserFullInfoDto createAppUser(NewAppUserDto newAppUserDto);

    AppUserFullInfoDto updateAppUserLoginName(UUID id, UpdateLoginNameDto updateLoginNameDto);

    AppUserFullInfoDto updateAppUserPrivacySettings(UUID id, UpdatePrivacySettingsDto updatePrivacySettingsDto);

    List<AppUserDto> allAppUsers();
}

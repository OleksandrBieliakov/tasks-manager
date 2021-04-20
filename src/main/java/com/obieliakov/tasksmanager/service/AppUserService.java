package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.appUser.*;
import com.obieliakov.tasksmanager.model.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    AppUser appUserModelById(UUID id);

    AppUser appUserModelByLoginName(String loginName);

    AppUserDto appUserById(UUID id, boolean isAdmin);

    AppUserDto appUserByLoginName(String loginName, boolean isAdmin);

    AppUser synchroniseWithIdentity();

    AppUserFullInfoDto profile();

    AppUserFullInfoDto createAppUser(NewAppUserDto newAppUserDto);

    AppUserFullInfoDto updateAppUserLoginName(UpdateLoginNameDto updateLoginNameDto);

    AppUserFullInfoDto updateAppUserPrivacySettings(UpdatePrivacySettingsDto updatePrivacySettingsDto);

    List<AppUserDto> allAppUsers();
}

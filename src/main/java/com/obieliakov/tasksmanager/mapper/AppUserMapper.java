package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.appUser.*;
import com.obieliakov.tasksmanager.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDto appUserToAppUserDto(AppUser appUser);

    List<AppUserDto> appUserListToAppUserDtoList(List<AppUser> appUserList);

    AppUser appUserDtoToAppUser(AppUserDto appUserDto);

    AppUser newAppUserDtoToAppUser(NewAppUserDto newAppUserDto);

    AppUser copyFromUpdateLoginNameDtoToAppUser(UpdateLoginNameDto updateLoginNameDto, @MappingTarget AppUser appUser);

    AppUser copyFromUpdatePrivacySettingsDtoToAppUser(UpdatePrivacySettingsDto updatePrivacySettingsDto, @MappingTarget AppUser appUser);

    AppUser copyFromAppUserIdentityDtoToAppUser(AppUserIdentityDto updateLoginNameDto, @MappingTarget AppUser appUser);

    AppUserFullInfoDto appUserToAppUserFullInfoDto(AppUser appUser);
}
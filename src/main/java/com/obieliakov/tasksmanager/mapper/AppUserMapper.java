package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.appUser.*;
import com.obieliakov.tasksmanager.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    default void setPrivateFieldsToNull(AppUserDto appUserDto, AppUser appUser) {
        if(!appUser.getPublicEmail()) {
            appUserDto.setEmail(null);
        }
        if(!appUser.getPublicFirstLastName()) {
            appUserDto.setFirstName(null);
            appUserDto.setLastName(null);
        }
    }

    AppUserDto appUserToAppUserDtoUnconditional(AppUser appUser);

    default AppUserDto appUserToAppUserDto(AppUser appUser, boolean unconditional) {
        if(appUser == null) {
            return null;
        }
        AppUserDto appUserDto = appUserToAppUserDtoUnconditional(appUser);
        if(!unconditional) {
            setPrivateFieldsToNull(appUserDto, appUser);
        }
        return appUserDto;
    }

    List<AppUserDto> appUserListToAppUserDtoListUnconditional(List<AppUser> appUserList);

    default List<AppUserDto> appUserListToAppUserDtoList(List<AppUser> appUserList, boolean unconditional) {
        if(appUserList == null) {
            return null;
        }
        return appUserList.stream().map(appUser -> appUserToAppUserDto(appUser, unconditional)).collect(Collectors.toList());
    }

    AppUser appUserDtoToAppUser(AppUserDto appUserDto);

    AppUser newAppUserDtoToAppUser(NewAppUserDto newAppUserDto);

    AppUser copyFromUpdateLoginNameDtoToAppUser(UpdateLoginNameDto updateLoginNameDto, @MappingTarget AppUser appUser);

    AppUser copyFromUpdatePrivacySettingsDtoToAppUser(UpdatePrivacySettingsDto updatePrivacySettingsDto, @MappingTarget AppUser appUser);

    AppUser copyFromAppUserIdentityDtoToAppUser(AppUserIdentityDto updateLoginNameDto, @MappingTarget AppUser appUser);

    AppUserFullInfoDto appUserToAppUserFullInfoDto(AppUser appUser);
}
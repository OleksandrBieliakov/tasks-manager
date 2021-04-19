package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserWithPrivacyMapper {

    private final AppUserMapper appUserMapper;

    public AppUserWithPrivacyMapper(AppUserMapper appUserMapper) {
        this.appUserMapper = appUserMapper;
    }

    private void setPrivateFieldsToNull(AppUserDto appUserDto, AppUser appUser) {
        if(!appUser.getPublicEmail()) {
            appUserDto.setEmail(null);
        }
        if(!appUser.getPublicFirstLastName()) {
            appUserDto.setFirstName(null);
            appUserDto.setLastName(null);
        }
    }

    public AppUserDto appUserToAppUserDtoWithPrivacy(AppUser appUser) {
        if(appUser == null) {
            return null;
        }
        AppUserDto appUserDto = appUserMapper.appUserToAppUserDto(appUser);
        setPrivateFieldsToNull(appUserDto, appUser);
        return appUserDto;
    }

    public List<AppUserDto> appUserListToAppUserDtoListWithPrivacy(List<AppUser> appUserList) {
        if(appUserList == null) {
            return null;
        }
        return appUserList.stream().map(this::appUserToAppUserDtoWithPrivacy).collect(Collectors.toList());
    }
}

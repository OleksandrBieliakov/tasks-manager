package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.dto.NewAppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDto appUserToAppUserDto(AppUser appUser);

    AppUser appUserDtoToAppUser(AppUserDto appUserDto);

    List<AppUserDto> appUserToAppUserDtoList(List<AppUser> appUserList);

    List<AppUser> appUserDtoToAppUserList(List<AppUserDto> appUserList);

    AppUser newAppUserDtoToAppUser(NewAppUserDto newAppUserDto);
}
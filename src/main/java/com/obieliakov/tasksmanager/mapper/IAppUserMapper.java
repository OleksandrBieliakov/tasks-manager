package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAppUserMapper {

    AppUserDto appUserToAppUserDto(AppUser appUser);

    AppUser appUserDtoToAppUser(AppUserDto appUserDto);
}
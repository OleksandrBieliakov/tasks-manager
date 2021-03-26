package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.AppUserDto;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    Optional<AppUserDto> findById(Long id);

    AppUserDto save(AppUserDto appUserDto);

    List<AppUserDto> findAll();
}

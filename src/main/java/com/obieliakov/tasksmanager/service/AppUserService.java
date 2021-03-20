package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.AppUserDto;

import java.util.Optional;

public interface AppUserService {
    Optional<AppUserDto> findById(Long id);

    AppUserDto save(AppUserDto foo);

    Iterable<AppUserDto> findAll();
}

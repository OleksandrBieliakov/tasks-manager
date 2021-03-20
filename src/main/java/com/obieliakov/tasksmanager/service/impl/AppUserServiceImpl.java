package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUserDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public AppUserDto save(AppUserDto foo) {
        return null;
    }

    @Override
    public Iterable<AppUserDto> findAll() {
        return null;
    }

}

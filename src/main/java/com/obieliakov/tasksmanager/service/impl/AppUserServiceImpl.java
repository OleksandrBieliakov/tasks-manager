package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.repository.IAppUserRepository;
import com.obieliakov.tasksmanager.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements IAppUserService {

    private IAppUserRepository appUserRepository;

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

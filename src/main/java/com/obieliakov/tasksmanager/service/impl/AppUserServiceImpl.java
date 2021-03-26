package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.dto.AppUserShortDto;
import com.obieliakov.tasksmanager.mapper.AppUserMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public Optional<AppUserDto> findById(Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        return appUser.map(appUserMapper::appUserToAppUserDto);
    }

    @Override
    public AppUserDto save(AppUserDto appUserDto) {
        AppUser appUser = appUserRepository.save(appUserMapper.appUserDtoToAppUser(appUserDto));
        return appUserMapper.appUserToAppUserDto(appUser);
    }

    @Override
    public List<AppUserDto> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUserMapper.appUserToAppUserDtoList(appUsers);
    }

    @Override
    public List<AppUserDto> getAppUsersWithMembershipInGroupWithId(Long id) {
        List<AppUser> appUsers = appUserRepository.queryAppUsersWithMembershipInGroupWithId(id);
        return appUserMapper.appUserToAppUserDtoList(appUsers);
    }

    @Override
    public List<AppUserShortDto> listAppUsersShortMembersOfGroupWithId(Long groupId) {
        return appUserRepository.queryAppUsersShortMembersOfGroupWithId(groupId);
    }
}

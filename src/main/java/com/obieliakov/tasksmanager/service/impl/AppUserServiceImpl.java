package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.AppUserShortDto;
import com.obieliakov.tasksmanager.dto.appUser.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.UpdatedAppUserInfoDto;
import com.obieliakov.tasksmanager.mapper.AppUserMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Validated
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final Validator validator;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper, Validator validator) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.validator = validator;
    }

    @Override
    public AppUserDto findById(Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUserMapper.appUserToAppUserDto(appUser.get());
    }

    @Override
    public AppUserDto findByLoginName(String loginName) {
        Optional<AppUser> appUser = appUserRepository.findAppUserByLoginName(loginName);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUserMapper.appUserToAppUserDto(appUser.get());
    }

    @Transactional
    @Override
    public AppUserDto createUser(NewAppUserDto newAppUserDto) {
        newAppUserDto.trim();

        Set<ConstraintViolation<NewAppUserDto>> violations = validator.validate(newAppUserDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        String loginName = newAppUserDto.getLoginName();
        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByLoginName(loginName);
        if (appUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login name is used by another user");
        }

        AppUser createdAppUser = appUserRepository.save(appUserMapper.newAppUserDtoToAppUser(newAppUserDto));
        return appUserMapper.appUserToAppUserDto(createdAppUser);
    }

    @Transactional
    @Override
    public AppUserDto updateUserInfo(Long userId, UpdatedAppUserInfoDto updatedAppUserInfoDto) {
        updatedAppUserInfoDto.trim();

        Set<ConstraintViolation<UpdatedAppUserInfoDto>> violations = validator.validate(updatedAppUserInfoDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Optional<AppUser> existingAppUserOptional = appUserRepository.findById(userId);
        if (existingAppUserOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with such id not found");
        }
        AppUser existingAppUser = existingAppUserOptional.get();

        existingAppUser.setFirstName(updatedAppUserInfoDto.getFirstName());
        existingAppUser.setLastName(updatedAppUserInfoDto.getLastName());

        AppUser updatedAppUser = appUserRepository.save(existingAppUser);
        return appUserMapper.appUserToAppUserDto(updatedAppUser);
    }

    @Override
    public List<AppUserDto> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUserMapper.appUserToAppUserDtoList(appUsers);
    }

    // added to practice custom hql query creation, better use some Group DTO
    @Override
    public List<AppUserDto> getAppUsersWithMembershipInGroupWithId(Long id) {
        List<AppUser> appUsers = appUserRepository.queryAppUsersWithMembershipInGroupWithId(id);
        return appUserMapper.appUserToAppUserDtoList(appUsers);
    }

    // added to practice custom hql query creation with projection better use some Group DTO
    @Override
    public List<AppUserShortDto> listAppUsersShortMembersOfGroupWithId(Long groupId) {
        return appUserRepository.queryAppUsersShortMembersOfGroupWithId(groupId);
    }
}

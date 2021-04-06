package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.NewAppUserDto;
import com.obieliakov.tasksmanager.dto.appUser.UpdateAppUserInfoDto;
import com.obieliakov.tasksmanager.mapper.AppUserMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
//@Validated // needed to validate functions parameters annotated with @Valid
public class AppUserServiceImpl implements AppUserService {

    private final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    private final Validator validator;

    private final AppUserMapper appUserMapper;

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper, Validator validator) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.validator = validator;
    }

    private AppUser appUserModelById(Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUser.get();
    }

    private AppUser appUserModelByLoginName(String loginName) {
        Optional<AppUser> appUser = appUserRepository.findByLoginName(loginName);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUser.get();
    }

    @Override
    public AppUserDto appUserById(Long id) {
        AppUser appUser = appUserModelById(id);
        return appUserMapper.appUserToAppUserDto(appUser);
    }

    @Override
    public AppUserDto appUserByLoginName(String loginName) {
        AppUser appUser = appUserModelByLoginName(loginName);
        return appUserMapper.appUserToAppUserDto(appUser);
    }

    @Override
    public AppUserDto createAppUser(NewAppUserDto newAppUserDto) {
        newAppUserDto.trim();

        Set<ConstraintViolation<NewAppUserDto>> violations = validator.validate(newAppUserDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Optional<AppUser> appUserOptional = appUserRepository.findByLoginName(newAppUserDto.getLoginName());
        if (appUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login name is used by another user");
        }

        AppUser newAppUser = appUserMapper.newAppUserDtoToAppUser(newAppUserDto);

        AppUser createdAppUser = appUserRepository.save(newAppUser);
        return appUserMapper.appUserToAppUserDto(createdAppUser);
    }

    @Override
    public AppUserDto updateAppUserInfo(Long id, UpdateAppUserInfoDto updateAppUserInfoDto) {
        updateAppUserInfoDto.trim();

        Set<ConstraintViolation<UpdateAppUserInfoDto>> violations = validator.validate(updateAppUserInfoDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        AppUser existingAppUser = appUserModelById(id);

        existingAppUser = appUserMapper.copyFromUpdateAppUserInfoDtoToAppUser(updateAppUserInfoDto, existingAppUser);

        AppUser updatedAppUser = appUserRepository.save(existingAppUser);
        return appUserMapper.appUserToAppUserDto(updatedAppUser);
    }

    @Override
    public List<AppUserDto> allAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUserMapper.appUserListToAppUserDtoList(appUsers);
    }
}

package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.*;
import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.groupinvite.GroupInviteDto;
import com.obieliakov.tasksmanager.mapper.AppUserMapper;
import com.obieliakov.tasksmanager.mapper.AppUserWithPrivacyMapper;
import com.obieliakov.tasksmanager.mapper.GroupInviteMapper;
import com.obieliakov.tasksmanager.mapper.GroupMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Group;
import com.obieliakov.tasksmanager.model.GroupInvite;
import com.obieliakov.tasksmanager.model.GroupMembership;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import com.obieliakov.tasksmanager.service.AppUserService;
import com.obieliakov.tasksmanager.service.IdentityService;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
//@Validated // needed to validate functions parameters annotated with @Valid
public class AppUserServiceImpl implements AppUserService {

    private final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    private final Validator validator;

    private final AppUserMapper appUserMapper;
    private final AppUserWithPrivacyMapper appUserWithPrivacyMapper;
    private final GroupMapper groupMapper;
    private final GroupInviteMapper groupInviteMapper;

    private final AppUserRepository appUserRepository;

    private final IdentityService identityService;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper, Validator validator, AppUserWithPrivacyMapper appUserWithPrivacyMapper, GroupMapper groupMapper, GroupInviteMapper groupInviteMapper, IdentityService identityService) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.validator = validator;
        this.appUserWithPrivacyMapper = appUserWithPrivacyMapper;
        this.groupMapper = groupMapper;
        this.groupInviteMapper = groupInviteMapper;
        this.identityService = identityService;
    }

    @Override
    public AppUser appUserModelById(UUID id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUser.get();
    }

    @Override
    public AppUser appUserModelByLoginName(String loginName) {
        Optional<AppUser> appUser = appUserRepository.findByLoginName(loginName);
        if (appUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUser.get();
    }

    @Override
    public AppUserDto appUserById(UUID id, boolean isAdmin) {
        AppUser appUser = appUserModelById(id);
        return isAdmin ?
                appUserMapper.appUserToAppUserDto(appUser) :
                appUserWithPrivacyMapper.appUserToAppUserDtoWithPrivacy(appUser);
    }

    @Override
    public AppUserDto appUserByLoginName(String loginName, boolean isAdmin) {
        AppUser appUser = appUserModelByLoginName(loginName);
        return isAdmin ?
                appUserMapper.appUserToAppUserDto(appUser) :
                appUserWithPrivacyMapper.appUserToAppUserDtoWithPrivacy(appUser);
    }

    @Override
    public AppUser synchroniseWithIdentity() {
        AppUserIdentityDto appUserIdentityDto = identityService.currentUser();
        AppUser existingAppUser = appUserModelById(appUserIdentityDto.getId());
        AppUser synchronizedAppUser = appUserMapper.copyFromAppUserIdentityDtoToAppUser(appUserIdentityDto, existingAppUser);
        return appUserRepository.save(synchronizedAppUser);
    }

    @Override
    public AppUserFullInfoDto profile() {
        AppUser synchronisedAppUser = synchroniseWithIdentity();
        return appUserMapper.appUserToAppUserFullInfoDto(synchronisedAppUser);
    }

    @Override
    public AppUserFullInfoDto createAppUser(NewAppUserDto newAppUserDto) {
        newAppUserDto.trim();

        Set<ConstraintViolation<NewAppUserDto>> violations = validator.validate(newAppUserDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        AppUserIdentityDto appUserIdentityDto = identityService.currentUser();

        Optional<AppUser> appUserOptionalById = appUserRepository.findById(appUserIdentityDto.getId());
        if (appUserOptionalById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        Optional<AppUser> appUserOptionalByLoginName = appUserRepository.findByLoginName(newAppUserDto.getLoginName());
        if (appUserOptionalByLoginName.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login name is used by another user");
        }

        AppUser newAppUser = appUserMapper.newAppUserDtoToAppUser(newAppUserDto);
        newAppUser = appUserMapper.copyFromAppUserIdentityDtoToAppUser(appUserIdentityDto, newAppUser);

        AppUser createdAppUser = appUserRepository.save(newAppUser);
        return appUserMapper.appUserToAppUserFullInfoDto(createdAppUser);
    }

    @Override
    public AppUserFullInfoDto updateAppUserLoginName(UpdateLoginNameDto updateLoginNameDto) {
        updateLoginNameDto.trim();

        Set<ConstraintViolation<UpdateLoginNameDto>> violations = validator.validate(updateLoginNameDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        UUID id = identityService.currentUserID();

        Optional<AppUser> appUserOptionalByLoginName = appUserRepository.findByLoginName(updateLoginNameDto.getLoginName());
        if (appUserOptionalByLoginName.isPresent() && !appUserOptionalByLoginName.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login name is used by another user");
        }

        AppUser existingAppUser = appUserModelById(id);

        existingAppUser = appUserMapper.copyFromUpdateLoginNameDtoToAppUser(updateLoginNameDto, existingAppUser);

        AppUser updatedAppUser = appUserRepository.save(existingAppUser);
        return appUserMapper.appUserToAppUserFullInfoDto(updatedAppUser);
    }

    @Override
    public AppUserFullInfoDto updateAppUserPrivacySettings(UpdatePrivacySettingsDto updatePrivacySettingsDto) {
        Set<ConstraintViolation<UpdatePrivacySettingsDto>> violations = validator.validate(updatePrivacySettingsDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        UUID id = identityService.currentUserID();

        AppUser existingAppUser = appUserModelById(id);

        existingAppUser = appUserMapper.copyFromUpdatePrivacySettingsDtoToAppUser(updatePrivacySettingsDto, existingAppUser);

        AppUser updatedAppUser = appUserRepository.save(existingAppUser);
        return appUserMapper.appUserToAppUserFullInfoDto(updatedAppUser);
    }

    @Override
    public AppUserGroupsDto appUserGroups() {
        UUID currentUserId = identityService.currentUserID();
        AppUser currentAppUser = appUserModelById(currentUserId);
        List<Group> appUserGroups = currentAppUser.getGroupMemberships().stream().map(GroupMembership::getGroup).collect(Collectors.toList());
        List<GroupInfoDto> groupInfoDtoList = groupMapper.groupListToGroupInfoDtoList(appUserGroups);
        AppUserGroupsDto appUserGroupsDto = new AppUserGroupsDto();
        appUserGroupsDto.setId(currentUserId);
        appUserGroupsDto.setGroupInfoDtoList(groupInfoDtoList);
        return appUserGroupsDto;
    }

    @Override
    public AppUserReceivedGroupInvitesDto appUserReceivedGroupInvites() {
        AppUser currentAppUser = appUserModelById(identityService.currentUserID());

        List<GroupInvite> groupInvites = currentAppUser.getGroupInvitesReceived();
        List<GroupInviteDto> groupInviteDtoList = groupInviteMapper.groupInviteListToGroupInviteDtoList(groupInvites);

        AppUserReceivedGroupInvitesDto appUserInvites = new AppUserReceivedGroupInvitesDto();
        appUserInvites.setReceivedGroupInvites(groupInviteDtoList);
        return appUserInvites;
    }

    @Override
    public List<AppUserDto> allAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUserMapper.appUserListToAppUserDtoList(appUsers);
    }
}

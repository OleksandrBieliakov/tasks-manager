package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserIdentityDto;
import com.obieliakov.tasksmanager.service.IdentityService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Override
    public AppUserIdentityDto currentUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                AppUserIdentityDto user = new AppUserIdentityDto();
                user.setId(UUID.fromString(keycloakPrincipal.getName()));
                AccessToken token = keycloakPrincipal.getKeycloakSecurityContext().getToken();
                user.setEmail(token.getEmail());
                user.setFirstName(token.getGivenName());
                user.setLastName(token.getFamilyName());
                //TODO set timeRegistered
                return user;
            } else {
                log.warn("Cannot get user data from principal");
            }
        } catch (Exception e) {
            log.warn("Cannot get principal: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public UUID currentUserID() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                return UUID.fromString(keycloakPrincipal.getName());
            } else {
                log.warn("Cannot get user data from principal");
            }
        } catch (Exception e) {
            log.warn("Cannot get principal: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean unauthorized(UUID checkedUserId) {
        return !currentUserID().equals(checkedUserId);
    }

    @Override
    public boolean unauthorized(AppUserIdentityDto currentUser, UUID checkedUserId) {
        return !currentUser.getId().equals(checkedUserId);
    }
}

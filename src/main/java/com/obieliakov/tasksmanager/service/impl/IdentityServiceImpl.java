package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.service.IdentityService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Override
    public AppUserDto currentUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                AppUserDto user = new AppUserDto();
                user.setLoginName(keycloakPrincipal.getName());
                AccessToken token = keycloakPrincipal.getKeycloakSecurityContext().getToken();
                user.setFirstName(token.getGivenName());
                user.setLastName(token.getFamilyName());
                return user;
            } else {
                log.warn("Cannot get user data from principal");
            }
        } catch (Exception e) {
            log.warn("Cannot get principal: {}", e.getMessage());
        }
        return null;
    }
}

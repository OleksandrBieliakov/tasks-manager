package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.NewAppUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AppUserServiceImplTest {

    private static final Integer MIN_LOGIN_NAME_LENGTH = 1;
    private static final Integer MAX_LOGIN_NAME_LENGTH = 50;
    private static final Integer OK_LOGIN_NAME_LENGTH = 10;
    private static final Boolean LOGIN_NAME_NOT_NULL = true;
    private static final Boolean LOGIN_NAME_NOT_EMPTY = true;
    private static final Boolean LOGIN_NAME_NOT_BLANK = true;

    @Autowired
    private AppUserServiceImpl appUserService;

    @Test
    void addUser() {
        if(MIN_LOGIN_NAME_LENGTH != null) {
            String tooShortLonginName = "a".repeat(MIN_LOGIN_NAME_LENGTH - 1);
            NewAppUserDto appUserDto = new NewAppUserDto();
            appUserDto.setLoginName(tooShortLonginName);
            assertThrows(ConstraintViolationException.class, () -> {
                appUserService.createUser(appUserDto);
            });
        }

        if(MAX_LOGIN_NAME_LENGTH != null) {
            String tooLongLoginName = "a".repeat(MAX_LOGIN_NAME_LENGTH + 1);
            NewAppUserDto appUserDto = new NewAppUserDto();
            appUserDto.setLoginName(tooLongLoginName);
            assertThrows(ConstraintViolationException.class, () -> {
                appUserService.createUser(appUserDto);
            });
        }

        if(LOGIN_NAME_NOT_NULL) {
            NewAppUserDto appUserDto = new NewAppUserDto();
            assertThrows(ConstraintViolationException.class, () -> {
                appUserService.createUser(appUserDto);
            });
        }

        if(LOGIN_NAME_NOT_EMPTY) {
            String emptyLoginName = "";
            NewAppUserDto appUserDto = new NewAppUserDto();
            appUserDto.setLoginName(emptyLoginName);
            assertThrows(ConstraintViolationException.class, () -> {
                appUserService.createUser(appUserDto);
            });
        }

        if(LOGIN_NAME_NOT_BLANK) {
            String blankLonginName = " ".repeat(OK_LOGIN_NAME_LENGTH);
            blankLonginName += "\n\n";
            NewAppUserDto appUserDto = new NewAppUserDto();
            appUserDto.setLoginName(blankLonginName);
            assertThrows(ConstraintViolationException.class, () -> {
                appUserService.createUser(appUserDto);
            });
        }

        if(OK_LOGIN_NAME_LENGTH != null) {
            String tooShortLonginName = "a".repeat(OK_LOGIN_NAME_LENGTH);
            NewAppUserDto appUserDto = new NewAppUserDto();
            appUserDto.setLoginName(tooShortLonginName);
            assertDoesNotThrow( () -> {
                appUserService.createUser(appUserDto);
            });
        }
    }
}
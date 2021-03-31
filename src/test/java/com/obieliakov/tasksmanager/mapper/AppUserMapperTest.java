package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.appUser.AppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AppUserMapperTest {

    @Autowired
    private AppUserMapper appUserMapper;

    @Test
    void appUserToAppUserDto() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setLoginName("Bob");
        AppUserDto appUserDto = appUserMapper.appUserToAppUserDto(appUser);
        assertEquals(1, appUserDto.getId());
        assertEquals("Bob", appUserDto.getLoginName());
    }

    @Test
    void appUserDtoToAppUser() {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(2L);
        appUserDto.setLoginName("Bill");
        AppUser appUser = appUserMapper.appUserDtoToAppUser(appUserDto);
        assertEquals(2, appUser.getId());
        assertEquals("Bill", appUser.getLoginName());
    }
}
package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IAppUserMapperTest {

    @Autowired
    IAppUserMapper appUserMapper;

    @Test
    void appUserToAppUserDto() {
        AppUser appUser = new AppUser(1L, "Bob");
        AppUserDto appUserDto = appUserMapper.appUserToAppUserDto(appUser);
        assertEquals(1, appUserDto.getId());
        assertEquals("Bob", appUserDto.getName());
    }
}
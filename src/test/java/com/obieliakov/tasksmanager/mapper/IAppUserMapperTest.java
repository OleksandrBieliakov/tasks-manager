package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.AppUserDto;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IAppUserMapperTest {

    @Autowired
    IAppUserMapper appUserMapper;

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

    //TODO delete or move to appUserRepository test class
    @Autowired
    AppUserRepository appUserRepository;

    @Test
    void appUserCreation() {
        AppUser bob = new AppUser();
        bob.setLoginName("Bob");
        appUserRepository.save(bob);
        AppUser mike = new AppUser();
        mike.setLoginName("Mike");
        appUserRepository.save(mike);
        assertEquals(2L, appUserRepository.count());
        //fail();
    }
}
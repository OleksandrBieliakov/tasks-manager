package com.obieliakov.tasksmanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

  /*  @Autowired
    GroupMembershipRepository groupMembershipRepository;


    //depends on current DatabaseLoaderTest
    @Test
    void queryAppUsersShort() {
        List<AppUserShortDto> appUsersShort1 = groupMembershipRepository.queryAppUsersShortByGroupId(1L);
        List<AppUserShortDto> appUsersShort2 = groupMembershipRepository.queryAppUsersShortByGroupId(2L);
        List<AppUserShortDto> appUsersShortEmpty = groupMembershipRepository.queryAppUsersShortByGroupId(3L);
        assertEquals(4, appUsersShort1.size());
        assertEquals(3, appUsersShort2.size());
        assertEquals(0, appUsersShortEmpty.size());
    }*/
}
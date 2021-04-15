package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.group.*;

import java.util.List;

public interface GroupService {

    GroupInfoDto groupInfoById(Long id);

    GroupInfoDto createGroup(NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupInfoDto updateGroupInfo(Long id, NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupMembersDto groupMembersById(Long id, boolean isAdmin);

    GroupMembersShortDto groupMembersShortById(Long id);

    GroupTasksDto groupTasksById(Long id, boolean isAdmin);

    List<GroupInfoDto> allGroups();
}

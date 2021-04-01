package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.group.GroupInfoDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersDto;
import com.obieliakov.tasksmanager.dto.group.GroupMembersShortDto;
import com.obieliakov.tasksmanager.dto.group.NewOrUpdateGroupDto;

public interface GroupService {

    GroupInfoDto groupInfoById(Long id);

    GroupInfoDto createGroup(NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupInfoDto updateGroupInfo(Long id, NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupMembersDto groupMembersById(Long id);

    GroupMembersDto groupMembersCustomById(Long id);

    GroupMembersShortDto groupMembersCustomShortById(Long id);
}

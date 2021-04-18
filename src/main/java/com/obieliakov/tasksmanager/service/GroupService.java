package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.group.*;
import com.obieliakov.tasksmanager.model.Group;

import java.util.List;

public interface GroupService {

    Group groupModelById(Long id);

    GroupInfoDto groupInfoById(Long id, boolean isAdmin);

    GroupInfoDto createGroup(NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupInfoDto updateGroupInfo(Long id, NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupMembersDto groupMembersById(Long id, boolean isAdmin);

    GroupTasksDto groupTasksById(Long id, boolean isAdmin);

    List<GroupInfoDto> allGroups();

    GroupMembersShortDto groupMembersShortById(Long id);
}

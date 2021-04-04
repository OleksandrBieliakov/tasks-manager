package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.group.*;

public interface GroupService {

    GroupInfoDto groupInfoById(Long id);

    GroupInfoDto createGroup(NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupInfoDto updateGroupInfo(Long id, NewOrUpdateGroupDto newOrUpdateGroupDto);

    GroupMembersDto groupMembersById(Long id);

    GroupMembersShortDto groupMembersShortById(Long id);

    GroupTasksDto groupTasksById(Long id);
}

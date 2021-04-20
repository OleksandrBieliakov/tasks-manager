package com.obieliakov.tasksmanager.dto.appUser;

import com.obieliakov.tasksmanager.dto.groupinvite.GroupInviteDto;
import lombok.Data;

import java.util.List;

@Data
public class AppUserReceivedGroupInvitesDto {

    private List<GroupInviteDto> receivedGroupInvites;
}

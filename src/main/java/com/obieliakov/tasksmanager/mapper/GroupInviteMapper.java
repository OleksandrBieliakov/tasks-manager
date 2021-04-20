package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.groupinvite.GroupInviteDto;
import com.obieliakov.tasksmanager.model.GroupInvite;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AppUserWithPrivacyMapper.class, GroupMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GroupInviteMapper {

    GroupInviteDto groupInviteToGroupInviteDto(GroupInvite groupInvite);

    List<GroupInviteDto> groupInviteListToGroupInviteDtoList(List<GroupInvite> groupInviteList);
}

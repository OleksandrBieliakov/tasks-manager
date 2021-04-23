package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.groupMembership.GroupMembershipDto;
import com.obieliakov.tasksmanager.model.GroupMembership;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = AppUserWithPrivacyMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GroupMembershipMapper {

    GroupMembershipDto groupMembershipToGroupMembershipDto(GroupMembership groupMembership);
}

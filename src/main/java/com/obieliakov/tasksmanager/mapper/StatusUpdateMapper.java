package com.obieliakov.tasksmanager.mapper;


import com.obieliakov.tasksmanager.dto.statusupdate.StatusUpdateDto;
import com.obieliakov.tasksmanager.model.StatusUpdate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusUpdateMapper {

    StatusUpdateDto statusUpdateToStatusUpdateDto(StatusUpdate statusUpdate);

    List<StatusUpdateDto> statusUpdateListToStatusUpdateDtoList(List<StatusUpdate> statusUpdate);
}

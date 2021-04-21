package com.obieliakov.tasksmanager.mapper;

import com.obieliakov.tasksmanager.dto.assignment.AssignmentDto;
import com.obieliakov.tasksmanager.dto.assignment.AssignmentShortDto;
import com.obieliakov.tasksmanager.model.Assignment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = AppUserWithPrivacyMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AssigmentMapper {

    AssignmentShortDto assignmentToAssignmentShortDto(Assignment assignment);

    AssignmentDto assignmentToAssignmentDto(Assignment assignment);
}

package com.obieliakov.tasksmanager.service;

import com.obieliakov.tasksmanager.dto.assignment.AssignmentDto;
import com.obieliakov.tasksmanager.dto.assignment.NewAssignmentDto;
import com.obieliakov.tasksmanager.model.Assignment;

public interface AssignmentService {

    Assignment assignmentModelById(Long id);

    AssignmentDto assignmentById(Long id);

    AssignmentDto createAssignment(NewAssignmentDto newAssignmentDto);
}

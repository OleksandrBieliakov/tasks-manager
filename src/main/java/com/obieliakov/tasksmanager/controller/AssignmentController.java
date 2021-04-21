package com.obieliakov.tasksmanager.controller;

import com.obieliakov.tasksmanager.dto.assignment.AssignmentDto;
import com.obieliakov.tasksmanager.dto.assignment.NewAssignmentDto;
import com.obieliakov.tasksmanager.service.AssignmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/assignments")
@SecurityRequirement(name = "identity")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("{id}")
    public AssignmentDto assignmentInfo(@PathVariable Long id) {
        return assignmentService.assignmentById(id);
    }

    @PostMapping
    public AssignmentDto createAssignment(@RequestBody NewAssignmentDto newAssignmentDto) {
        return assignmentService.createAssignment(newAssignmentDto);
    }

    @DeleteMapping("{id}")
    public void deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
    }
}

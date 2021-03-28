package com.obieliakov.tasksmanager.errorhandling;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();
}

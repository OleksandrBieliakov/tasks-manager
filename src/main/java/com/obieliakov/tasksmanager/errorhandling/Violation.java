package com.obieliakov.tasksmanager.errorhandling;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Violation {

    private final String fieldName;

    private final String message;
}
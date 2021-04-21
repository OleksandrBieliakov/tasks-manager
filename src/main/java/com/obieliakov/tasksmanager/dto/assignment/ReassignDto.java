package com.obieliakov.tasksmanager.dto.assignment;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ReassignDto {

    @NotNull
    private Long id;

    @NotNull
    private UUID toAppUserId;
}

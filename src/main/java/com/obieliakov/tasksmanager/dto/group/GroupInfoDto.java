package com.obieliakov.tasksmanager.dto.group;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GroupInfoDto {

    private Long id;
    private String name;
    private ZonedDateTime timeCreated;
}

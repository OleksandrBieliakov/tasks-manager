package com.obieliakov.tasksmanager.dto.group;

import com.obieliakov.tasksmanager.model.Group;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class NewOrUpdateGroupDto {

    @NotNull
    @Size(min = Group.NAME_MIN_LENGTH, max = Group.NAME_MAX_LENGTH)
    private String name;

    public void trim() {
        if(name != null) {
            name = name.trim();
        }
    }
}
